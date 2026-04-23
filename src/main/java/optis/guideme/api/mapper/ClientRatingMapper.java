package optis.guideme.api.mapper;

import optis.guideme.api.dto.response.ClientRatingDto;
import optis.guideme.domain.user.ClientRating;

public class ClientRatingMapper {
    public static ClientRatingDto toDto(ClientRating rating) {
        return new ClientRatingDto(
                rating.getClientName(),
                rating.getRating(),
                rating.getDate()
        );
    }
}
