package optis.guideme.domain;

import java.time.LocalDateTime;

public class Reminder {
    private String title;
    private String description;
    private LocalDateTime sendAt;
    private Meeting meeting;
}
