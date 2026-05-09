package optis.guideme.api.dto.response;

import java.time.LocalDate;

public record ClientScoreDto(String clientName, int score, LocalDate date) {
}
