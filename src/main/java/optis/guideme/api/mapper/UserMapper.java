package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewUserDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.domain.factories.UserFactory;
import optis.guideme.domain.user.Consultant;
import optis.guideme.domain.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final UserFactory userFactory;

    public User toDomain(NewUserDto newUserDto) {
       // return userFactory.addNewUser(newUserDto);
        return null; //TODO
    }

    public UserDto toResponseDto(User user) {
        String clientName  = null; // null when not a consultant
        LocalDateTime startDateAtClient = null; // null when not a consultant

        if (user instanceof Consultant consultant) {
            clientName  = consultant.getClientName();
            startDateAtClient = consultant.getStartDateAtClient();
        }

        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                clientName,
                startDateAtClient,
                user.getAvatar(),
                user.getRole()
        );
    }
}
