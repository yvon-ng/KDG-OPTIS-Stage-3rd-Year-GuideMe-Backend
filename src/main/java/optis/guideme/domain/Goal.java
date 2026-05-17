package optis.guideme.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.WrongInputException;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Table(name = "goals")
@AllArgsConstructor
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue
    private UUID goalId;
    private UUID userId;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private LocalDate achievementDate;

    public Goal(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.dueDate = dueDate;
    }

    public void assignUser(UUID userId) {
        this.userId = userId;
    }

    public void complete() {
        if (this.achievementDate != null) throw new WrongInputException("Goal is already completed");
        achievementDate = LocalDate.now();
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
