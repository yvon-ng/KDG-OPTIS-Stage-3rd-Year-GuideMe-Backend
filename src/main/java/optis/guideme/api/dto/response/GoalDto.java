package optis.guideme.api.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record GoalDto(UUID goalId, String title, String description, LocalDate creationDate, LocalDate dueDate, LocalDate achievementDate) {
}
