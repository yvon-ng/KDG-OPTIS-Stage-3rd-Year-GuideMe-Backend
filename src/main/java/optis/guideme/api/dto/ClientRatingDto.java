package optis.guideme.api.dto;

import optis.guideme.domain.user.client.Client;
import optis.guideme.domain.user.client.ClientRating;
import optis.guideme.domain.user.client.Rating;

import java.time.LocalDate;

public record ClientRatingDto(Client client, Rating rating, LocalDate date) {
    public static ClientRatingDto fromDomain(ClientRating clientRating) {
        return new ClientRatingDto(
                clientRating.client(),
                clientRating.rating(),
                clientRating.date()
        );
    }
}
