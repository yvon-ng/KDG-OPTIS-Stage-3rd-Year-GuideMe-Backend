package optis.guideme.domain;

import optis.guideme.domain.user.UserId;

import java.time.LocalDate;

public class Goal {
    private String title;
    private String description;
    private LocalDate dateOfCreation;
    private LocalDate dueDate;
    private LocalDate dateOfAchievement;

    private UserId userId;

    public boolean isAchieved() {
        return dateOfAchievement != null;
    }
}
