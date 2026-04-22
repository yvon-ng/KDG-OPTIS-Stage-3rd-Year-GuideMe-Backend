package optis.guideme.api.dto;

import optis.guideme.domain.user.Role;

public record NewInvitationDto(String email, Role role) {
}
