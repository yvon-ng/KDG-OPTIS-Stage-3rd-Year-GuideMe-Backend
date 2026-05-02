package optis.guideme.api.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserDto(UUID userId, String firstName, String lastName, String email, String clientName, LocalDate startDateAtClient, List<ClientRatingDto> clientRatings, String avatar) {
}
