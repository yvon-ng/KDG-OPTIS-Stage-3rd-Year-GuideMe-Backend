package optis.guideme.api.dto;

import optis.guideme.domain.user.Consultant;
import optis.guideme.domain.user.User;

import java.time.LocalDate;
import java.util.List;

public record UserDto(String firstName, String lastName, String email, String currentClient,
                      LocalDate startDateAtClient, List<ClientRatingDto> clientRatings) {
    public static UserDto fromDomain(User user) {
        String currentClient = null;
        LocalDate startDateAtClient = null;
        List<ClientRatingDto> clientRatings = List.of();

        if (user instanceof Consultant consultant) {
            if (consultant.getClient() != null) {
                currentClient = consultant.getClient().clientName();
                startDateAtClient = consultant.getClient().startDateAtClient();
            }

            clientRatings = consultant.getClientRatings()
                    .stream()
                    .map(ClientRatingDto::fromDomain)
                    .toList();
        }

        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                currentClient,
                startDateAtClient,
                clientRatings
        );
    }
}
