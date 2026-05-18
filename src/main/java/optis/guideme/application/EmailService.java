package optis.guideme.application;

import optis.guideme.api.dto.response.UserDto;
import optis.guideme.domain.meeting.Attendee;
import org.springframework.core.io.ClassPathResource;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import lombok.RequiredArgsConstructor;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static optis.guideme.domain.meeting.Reminder.EMAIL_REMINDER_HOURS_BEFORE_MEETING;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {
    private static final String FROM_EMAIL = "onboarding@resend.dev"; //TODO moet veranderd worden naar eigen DNS for dev only works with own email

    private final UserService userService;
    private final Resend resend;
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);


    public void sendEmailReminder(UUID userId, Meeting meeting) {
        User user = userService.getUser(userId);
        String scheduledAt = meeting.getStartDateTime()
                .atZone(ZoneId.systemDefault())
                .minusHours(EMAIL_REMINDER_HOURS_BEFORE_MEETING) // TODO check if correct
                .toInstant()
                .toString();
        sendEmail(user, meeting, "templates/emails/meeting-reminder.html", "Reminder for Meeting " + meeting.getDisplayName(), scheduledAt);
    }

    public void sendAcceptedEmail(UUID userId, Meeting meeting) {
        User user = userService.getUser(userId);
        sendEmail(user, meeting, "templates/emails/meeting-request-accepted.html", "Meeting Request Accepted for " + meeting.getDisplayName(), null);
    }

    private void sendEmail(User user, Meeting meeting, String templatePath, String subject, String scheduledAt) {
        try {
            String html = buildMeetingTemplate(user, meeting, templatePath);
            CreateEmailOptions.Builder builder = CreateEmailOptions.builder()
                    .from(FROM_EMAIL)
                    .to(user.getEmail())
                    .subject(subject)
                    .html(html);
            if (scheduledAt != null) builder.scheduledAt(scheduledAt);
            resend.emails().send(builder.build());
        } catch (ResendException | IOException exception) {
            logger.warn("Failed to send email to {} {} for meeting {}: {}", user.getFirstName(), user.getLastName(), meeting.getDisplayName(), exception.getMessage());
        }
    }

    private String buildMeetingTemplate(User user, Meeting meeting, String templatePath) throws IOException {
        List<UserDto> users = userService.getByIds(meeting.getAttendees().stream().map(Attendee::getUserId).toList());
        ClassPathResource resource = new ClassPathResource(templatePath);
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return html
                .replace("{{UserName}}", user.getFirstName())
                .replace("{{MeetingDisplayName}}", meeting.getDisplayName())
                .replace("{{HoursBeforeStart}}", String.valueOf(EMAIL_REMINDER_HOURS_BEFORE_MEETING))
                .replace("{{MeetingTime}}", meeting.getStartDateTime().toLocalTime().withNano(0).toString())
                .replace("{{MeetingDate}}", meeting.getStartDateTime().toLocalDate().toString())
                .replace("{{MeetingAttendees}}", buildAttendees(users));
    }

    private String buildAttendees(List<UserDto> users) {
        return users.stream().map(u -> u.firstName() + " " + u.lastName()).reduce((a, b) -> a + ", " + b).orElse("No attendees");
    }
}
