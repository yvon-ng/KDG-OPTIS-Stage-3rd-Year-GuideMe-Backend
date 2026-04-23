package optis.guideme.api.dto;

import optis.guideme.domain.user.Consultant;
import optis.guideme.domain.user.User;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public record UserDto(String firstName, String lastName, String email, String clientName, LocalDate startDateAtClient, List<ClientRatingDto> clientRatings) {
    public static UserDto fromDomain(User user) {
        String clientName  = null; // null when not a consultant
        LocalDate startDateAtClient = null; // null when not a consultant
        List<ClientRatingDto> clientRatings = List.of();

        if (user instanceof Consultant consultant) {
            clientName  = consultant.getClientName();

            clientRatings = consultant.getClientRatings()
                    .stream()
                    .map(ClientRatingDto::fromDomain)
                    .toList();

            startDateAtClient = consultant.getStartDateAtClient();
        }

        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                clientName,
                startDateAtClient,
                clientRatings
        );
    }
}
