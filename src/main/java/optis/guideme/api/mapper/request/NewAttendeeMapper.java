package optis.guideme.api.mapper.request;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewAttendeeDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class NewAttendeeMapper {
//    private final UserRepository userRepository;

    public Attendee toDomain(NewAttendeeDto newAttendeeDto){
        //UUID userId = newAttendeeDto.userId();
//        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return new Attendee(
                newAttendeeDto.userId(),
                newAttendeeDto.role()
        );
    }

    public AttendeeDto toResponseDto(Attendee attendee) {
        //UUID userId = attendee.getUserId();
//        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return new AttendeeDto(
                attendee.getUserId()
        );
    }
}
