package optis.guideme.api.dto.request;

import optis.guideme.domain.user.Role;

import java.time.LocalDate;
import java.util.UUID;

public record NewUserDto(String firstName, String lastName, String email, Role role) {
}
