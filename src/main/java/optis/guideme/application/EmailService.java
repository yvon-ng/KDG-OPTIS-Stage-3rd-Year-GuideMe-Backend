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
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {
    private final UserService userService;
    private final Resend resend;
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmailReminder(UUID userId, Meeting meeting){
        User user = userService.getUserEntity(userId);

        List<UserDto> users = userService.getUsersByIds(
                meeting.getAttendees()
                        .stream()
                        .map(Attendee::getUserId)
                        .toList()
        );

        try {
            ClassPathResource resource = new ClassPathResource("templates/emails/meeting-reminder.html");


            String html = new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );

            html = html
                    .replace("{{UserName}}", user.getFirstName())
                    .replace("{{MeetingDisplayName}}", meeting.getDisplayName())
                    .replace("{{MeetingTime}}", meeting.getStartDateTime().toLocalTime().withNano(0).toString())
                    .replace("{{MeetingDate}}", meeting.getStartDateTime().toLocalDate().toString())
                    .replace("{{MeetingAttendees}}",
                            users.stream()
                                    .map(u -> u.firstName() + " " + u.lastName())
                                    .reduce((a, b) -> a + ", " + b)
                                    .orElse("No attendees")
                    );

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("onboarding@resend.dev") //TODO moet veranderd worden naar eigen DNS for dev only works with own email
                .to(user.getEmail())
                .subject("Reminder for meeting " + meeting.getDisplayName())
                .html(html)
                .build();

            resend.emails().send(params);
        } catch (ResendException | IOException exception ) {
            logger.warn("Failed to send email to {} {}: \n {}",user.getFirstName(), user.getLastName(), exception.getMessage());
        }
    }
}
