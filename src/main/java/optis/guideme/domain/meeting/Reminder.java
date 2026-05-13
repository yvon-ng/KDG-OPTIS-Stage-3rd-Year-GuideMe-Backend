package optis.guideme.domain.meeting;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reminders")
@Getter
public class Reminder {
    public static final int EMAIL_REMINDER_HOURS_BEFORE_MEETING = 1;

    @Id
    @GeneratedValue
    private UUID reminderId;
    private UUID receiver;
    private UUID meetingId;
    private LocalDateTime scheduledAt;
    private boolean sent;

    public Reminder(UUID receiver, Meeting meeting) {
        this.receiver = receiver;
        this.meetingId = meeting.getMeetingId();
        this.scheduledAt = meeting.getStartDateTime().minusHours(Reminder.EMAIL_REMINDER_HOURS_BEFORE_MEETING);
    }

    public void markAsSent() {
        this.sent = true;
    }

}
