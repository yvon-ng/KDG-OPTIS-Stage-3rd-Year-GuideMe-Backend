package optis.guideme.domain.meeting;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reminder {
    public static final int EMAIL_REMINDER_HOURS_BEFORE_MEETING  = 1;

    private UUID reminderId;
    private UUID userId;
    private UUID meetingId;
    private String title;
    private String description;
    private LocalDateTime scheduledAt;

    public boolean isSent(){
        return scheduledAt.isBefore(LocalDateTime.now()); //todo
    }
}
