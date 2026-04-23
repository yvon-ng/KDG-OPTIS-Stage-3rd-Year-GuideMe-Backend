package optis.guideme.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Goal {
    private String title;
    private String description;
    private LocalDate dateOfCreation;
    private LocalDate dueDate;
    private LocalDate dateOfAchievement;

    private UUID userId;

    public boolean isAchieved() {
        return dateOfAchievement != null;
    }
}
