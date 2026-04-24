package optis.guideme.api.mapper.request;

import optis.guideme.api.dto.request.NewAttendeeDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRepository;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class NewAttendeeMapper {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    public NewAttendeeMapper(MeetingRepository meetingRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
    }

//    public NewAttendeeDto toDto(Attendee attendee){
//        return new NewAttendeeDto(
//                attendee.getUser().getUserId()
//        );
//    }

    public Attendee toDomain(NewAttendeeDto newAttendeeDto){
       User user = userRepository.findById(newAttendeeDto.userId()).orElseThrow(() -> new NotFoundException("User not found with id " + newAttendeeDto.userId()));
        return new Attendee(
                Role.valueOf(user.getClass().getSimpleName().toUpperCase()),
                user
        );
    }

    public AttendeeDto toResponseDto(Attendee attendee) {
        return new AttendeeDto(
                attendee.getUser().getUserId()
        );
    }

}
