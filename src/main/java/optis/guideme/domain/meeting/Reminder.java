package optis.guideme.domain.meeting;

import optis.guideme.domain.user.UserId;

import java.time.LocalDateTime;

public class Reminder {
    private String title;
    private String description;
    private LocalDateTime sendAt;
    private Meeting meeting;

    private UserId userId;
}
