package optis.guideme.api.dto.response;

import java.time.LocalDateTime;

public record ClientScoreDto(String clientName, int score, LocalDateTime date) {
}
