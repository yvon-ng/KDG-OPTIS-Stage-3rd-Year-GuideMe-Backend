package optis.guideme.domain.meeting;

import optis.guideme.domain.user.UserId;

import java.time.LocalDateTime;

public class Reminder {
    private String title;
    private String description;
    private LocalDateTime scheduledAt;
    private Meeting meeting;
    public boolean isSent(){
        return scheduledAt.isBefore(LocalDateTime.now());
    }
}
