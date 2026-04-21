package optis.guideme.api.dto;

import optis.guideme.domain.user.Consultant;
import optis.guideme.domain.user.User;
import optis.guideme.domain.user.client.ClientAssignment;

import java.util.List;

public record UserDto(String firstName, String lastName, String email, ClientAssignment clientAssignment, List<ClientRatingDto> clientRatings) {
    public static UserDto fromDomain(User user) {
        ClientAssignment clientAssignment  = null; // null when not a consultant
        List<ClientRatingDto> clientRatings = List.of();

        if (user instanceof Consultant consultant) {
            clientAssignment  = consultant.getClientAssignment();

            clientRatings = consultant.getClientRatings()
                    .stream()
                    .map(ClientRatingDto::fromDomain)
                    .toList();
        }

        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                clientAssignment,
                clientRatings
        );
    }
}
