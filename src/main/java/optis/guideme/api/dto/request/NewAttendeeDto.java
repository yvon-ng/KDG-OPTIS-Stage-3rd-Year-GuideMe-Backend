package optis.guideme.api.dto.request;
import optis.guideme.domain.user.Role;

import java.util.UUID;

public record NewAttendeeDto(UUID userId, Role role){

}
