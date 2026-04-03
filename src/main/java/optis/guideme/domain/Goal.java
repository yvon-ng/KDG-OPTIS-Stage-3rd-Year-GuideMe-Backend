package optis.guideme.domain;

import optis.guideme.domain.user.UserId;

import java.time.LocalDateTime;

public class Goal {
    private String title;
    private String description;
    private boolean approved;
    private LocalDateTime startDate;
    private LocalDateTime dueDate; //TODO vraag na ofdat nodig is
    private LocalDateTime achievedDate;

    private UserId userId;
}
