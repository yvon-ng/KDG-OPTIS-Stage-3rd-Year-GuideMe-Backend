package optis.guideme.api.dto.response;

import java.time.LocalDate;

public record ClientRatingDto(String clientName, int rating, LocalDate date) {
}
