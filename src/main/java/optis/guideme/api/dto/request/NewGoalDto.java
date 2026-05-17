package optis.guideme.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record NewGoalDto(@NotNull String title, String description,@NotNull LocalDate dueDate) {
}
