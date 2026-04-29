package optis.guideme.api.dto.request;

import optis.guideme.domain.user.Role;

public record NewInvitationDto(String email, Role role) {
}
