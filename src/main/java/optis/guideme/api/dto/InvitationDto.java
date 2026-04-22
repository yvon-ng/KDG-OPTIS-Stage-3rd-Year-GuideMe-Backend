package optis.guideme.api.dto;

import optis.guideme.domain.user.Role;

public record InvitationDto(String email, Role role, String inviteLink) {
}
