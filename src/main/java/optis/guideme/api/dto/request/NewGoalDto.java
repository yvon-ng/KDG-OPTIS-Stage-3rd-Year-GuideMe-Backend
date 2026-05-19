package optis.guideme.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;


public record NewGoalDto(@NotNull String title, String description,@NotNull Instant dueDate) {
}
