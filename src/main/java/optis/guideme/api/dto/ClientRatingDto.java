package optis.guideme.api.dto;

//import optis.guideme.domain.user.client.Client;
import optis.guideme.domain.user.ClientRating;
import optis.guideme.domain.user.Rating;

import java.time.LocalDate;

public record ClientRatingDto(String clientName, Rating rating, LocalDate date) {
    public static ClientRatingDto fromDomain(ClientRating clientRating) {
        return new ClientRatingDto(
                clientRating.getClientName(),
                clientRating.getRating(),
                clientRating.getDate()
        );
    }
}
