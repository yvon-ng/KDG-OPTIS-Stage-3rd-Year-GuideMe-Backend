package optis.guideme.api.dto.response;

import java.util.UUID;

public record AttendeeDto(UUID userId, String firstName, String lastName) {
}
