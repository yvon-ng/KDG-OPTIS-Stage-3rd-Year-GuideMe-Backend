package optis.guideme.api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateGoalDto(@NotNull String title, String description, @NotNull LocalDate dueDate) {
}
