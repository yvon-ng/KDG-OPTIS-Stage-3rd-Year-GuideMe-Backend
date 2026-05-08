package optis.guideme.api.dto.request;
import jakarta.validation.constraints.NotNull;
import optis.guideme.domain.user.Role;

import java.util.UUID;

public record NewAttendeeDto(@NotNull UUID userId){

}
