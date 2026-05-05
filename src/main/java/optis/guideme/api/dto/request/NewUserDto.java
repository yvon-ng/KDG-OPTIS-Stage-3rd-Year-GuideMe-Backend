package optis.guideme.api.dto.request;

import optis.guideme.domain.user.Role;


public record NewUserDto(String firstName, String lastName, String email, Role role) {
}
