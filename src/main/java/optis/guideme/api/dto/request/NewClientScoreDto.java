package optis.guideme.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record NewClientScoreDto(@NotNull int score) {
}
