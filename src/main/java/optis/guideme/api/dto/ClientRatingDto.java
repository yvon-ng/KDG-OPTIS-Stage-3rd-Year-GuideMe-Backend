package optis.guideme.api.dto;

import optis.guideme.domain.user.ClientRating;
import optis.guideme.domain.user.Rating;

import java.time.LocalDate;

public record ClientRatingDto(String currentClient, Rating rating, LocalDate date) {
    public static ClientRatingDto fromDomain(ClientRating clientRating) {
        return new ClientRatingDto(
                clientRating.getCurrentClient(),
                clientRating.getRating(),
                clientRating.getDate()
        );
    }
}
