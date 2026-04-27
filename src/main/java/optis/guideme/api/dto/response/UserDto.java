package optis.guideme.api.dto.response;

import java.time.LocalDate;
import java.util.List;

public record UserDto(String firstName, String lastName, String email, String clientName, LocalDate startDateAtClient, List<ClientRatingDto> clientRatings, String avatar) {
}
