package optis.guideme.application;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.UserMapper;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static optis.guideme.domain.meeting.Reminder.EMAIL_REMINDER_HOURS_BEFORE_MEETING;
import static optis.guideme.domain.meeting.Reminder.TEST_EMAIL_REMINDER_MINUTES_BEFORE_MEETING;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {
    private static final String FROM_EMAIL = "onboarding@resend.dev"; //TODO moet veranderd worden naar eigen DNS for dev only works with own email
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final UserService userService;
    private final UserMapper userMapper;
    private final Resend resend;

    public void sendEmailReminder(UUID userId, Meeting meeting) {
        User user = userService.getUser(userId);
        String scheduledAt = meeting.getStartDateTime()
                .atZone(ZoneId.systemDefault())
                .minusMinutes(TEST_EMAIL_REMINDER_MINUTES_BEFORE_MEETING)
                .toInstant()
                .toString();
        String html = buildMeetingTemplate(user, meeting.getDisplayName(), meeting.getStartDateTime(), getMeetingAttendees(meeting), "templates/emails/meeting-reminder.html", null);
        sendEmail(user, meeting.getDisplayName(), "Reminder for Meeting " + meeting.getDisplayName(), html, scheduledAt);
    }

    public void sendAcceptedEmail(UUID userId, Meeting meeting) {
        User user = userService.getUser(userId);
        String html = buildMeetingTemplate(user, meeting.getDisplayName(), meeting.getStartDateTime(), getMeetingAttendees(meeting), "templates/emails/meeting-request-accepted.html", null);
        sendEmail(user, meeting.getDisplayName(), "Meeting Request Accepted for " + meeting.getDisplayName(), html, null);
    }

    public void sendDeclinedEmail(UUID userId, MeetingRequest meetingRequest) {
        User user = userService.getUser(userId);
        String html = buildMeetingTemplate(user, meetingRequest.getDisplayName(), meetingRequest.getStartDateTime(), getMeetingRequestAttendees(meetingRequest), "templates/emails/meeting-request-declined.html", null);
        sendEmail(user, meetingRequest.getDisplayName(), "Meeting Request Declined for " + meetingRequest.getDisplayName(), html, null);
    }

    public void sendRescheduledEmail(UUID userId, MeetingRequest newMeetingRequest, MeetingRequest oldMeetingRequest) {
        User user = userService.getUser(userId);
        String html = buildMeetingTemplate(user, newMeetingRequest.getDisplayName(), newMeetingRequest.getStartDateTime(), getMeetingRequestAttendees(newMeetingRequest), "templates/emails/meeting-request-rescheduled.html", oldMeetingRequest.getStartDateTime());
        sendEmail(user, newMeetingRequest.getDisplayName(), "Meeting Request Rescheduled for " + newMeetingRequest.getDisplayName(), html, null);
    }

    private void sendEmail(User user, String meetingName, String subject, String html, String scheduledAt) {
        try {
            CreateEmailOptions.Builder builder = CreateEmailOptions.builder()
                    .from(FROM_EMAIL)
                    .to(user.getEmail())
                    .subject(subject)
                    .html(html);
            if (scheduledAt != null) builder.scheduledAt(scheduledAt);
            resend.emails().send(builder.build());
        } catch (ResendException exception) {
            logger.warn("Failed to send email to {} {} for meeting {}: {}", user.getFirstName(), user.getLastName(), meetingName, exception.getMessage());
        }
    }

    private String buildMeetingTemplate(User user, String displayName, LocalDateTime startDateTime, List<UserDto> attendees, String templatePath, LocalDateTime oldStartDateTime) {
        try {
            String html = loadTemplate(templatePath);
            html = html
                    .replace("{{UserName}}", user.getFirstName())
                    .replace("{{MeetingDisplayName}}", displayName)
                    .replace("{{HoursBeforeStart}}", String.valueOf(EMAIL_REMINDER_HOURS_BEFORE_MEETING))
                    .replace("{{MeetingTime}}", startDateTime.format(TIME_FORMATTER))
                    .replace("{{MeetingDate}}", startDateTime.format(DATE_FORMATTER))
                    .replace("{{MeetingAttendees}}", buildAttendees(attendees));

            if (oldStartDateTime != null) {
                html = html
                        .replace("{{OldMeetingTime}}", oldStartDateTime.format(TIME_FORMATTER))
                        .replace("{{OldMeetingDate}}", oldStartDateTime.format(DATE_FORMATTER));
            }

            return html;
        } catch (IOException exception) {
            throw new RuntimeException("Failed to build email template", exception);
        }
    }

    private String loadTemplate(String templatePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(templatePath);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    private List<UserDto> getMeetingAttendees(Meeting meeting) {
        return userService.getByIds(meeting.getAttendees().stream().map(Attendee::getUserId).toList());
    }

    private List<UserDto> getMeetingRequestAttendees(MeetingRequest meetingRequest) {
        List<UserDto> users = new ArrayList<>();
        User requestedByUser = userService.getUser(meetingRequest.getRequestedBy());
        List<User> requestedForUsers = userService.getUsersByIds(meetingRequest.getRequestFor());
        users.add(userMapper.toResponseDto(requestedByUser));
        users.addAll(requestedForUsers.stream().map(userMapper::toResponseDto).toList());
        return users;
    }

    private String buildAttendees(List<UserDto> users) {
        return users.stream().map(u -> u.firstName() + " " + u.lastName()).reduce((a, b) -> a + ", " + b).orElse("No attendees");
    }
}