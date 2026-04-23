package optis.guideme.api.dto.response;

//import optis.guideme.domain.user.client.Client;
import optis.guideme.domain.user.ClientRating;
import optis.guideme.domain.user.Rating;

import java.time.LocalDate;

public record ClientRatingDto(String clientName, Rating rating, LocalDate date) {
}
