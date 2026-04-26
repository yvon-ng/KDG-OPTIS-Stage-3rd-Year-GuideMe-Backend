package optis.guideme.api.mapper.request;

import optis.guideme.api.dto.request.NewAttendeeDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class NewAttendeeMapper {
    private final UserRepository userRepository;

    public NewAttendeeMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Attendee toDomain(NewAttendeeDto newAttendeeDto){
       User user = userRepository.findById(newAttendeeDto.userId()).orElseThrow(() -> new NotFoundException("User not found with id " + newAttendeeDto.userId()));
        return new Attendee(
                Role.valueOf(user.getClass().getSimpleName().toUpperCase()),
                user
        );
    }

    public AttendeeDto toResponseDto(Attendee attendee) {
        User user = attendee.getUser();
        return new AttendeeDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

}
