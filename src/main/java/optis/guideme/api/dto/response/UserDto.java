package optis.guideme.api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(UUID userId, String firstName, String lastName, String email, String clientName, LocalDateTime startDateAtClient, String avatar) {
}
