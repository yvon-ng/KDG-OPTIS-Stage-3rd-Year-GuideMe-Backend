package optis.guideme.api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;


public record UpdateGoalDto(@NotNull String title, String description, @NotNull Instant dueDate) {
}
