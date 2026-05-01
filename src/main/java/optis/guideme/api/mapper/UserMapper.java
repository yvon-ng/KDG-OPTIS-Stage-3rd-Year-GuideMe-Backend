package optis.guideme.api.mapper;

import optis.guideme.api.dto.response.ClientRatingDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.domain.user.Consultant;
import optis.guideme.domain.user.User;

import java.time.LocalDate;
import java.util.List;

public class UserMapper {
    public static UserDto toDto(User user) {
        String clientName  = null; // null when not a consultant
        LocalDate startDateAtClient = null; // null when not a consultant
        List<ClientRatingDto> clientRatings = List.of();

        if (user instanceof Consultant consultant) {
            clientName  = consultant.getClientName();

            clientRatings = consultant.getClientRatings()
                    .stream()
                    .map(ClientRatingMapper::toDto)
                    .toList();

            startDateAtClient = consultant.getStartDateAtClient();
        }

        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                clientName,
                startDateAtClient,
                clientRatings,
                user.getAvatar(),
                user.getUserId()
        );
    }
}
