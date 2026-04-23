package optis.guideme.domain.meeting;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reminder {
    private String title;
    private String description;
    private LocalDateTime scheduledAt;
    private Meeting meeting;

    private UUID userId;

    public boolean isSent(){
        return scheduledAt.isBefore(LocalDateTime.now());
    }
}
