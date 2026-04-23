package optis.guideme.api.mapper;

import optis.guideme.api.dto.request.AttendeeDto;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRepository;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AttendeeMapper {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    public AttendeeMapper(MeetingRepository meetingRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
    }

    public AttendeeDto toDto(Attendee attendee){
        return new AttendeeDto(
                attendee.getUser().getUserId(),
                attendee.getMeeting().getMeetingId()
        );
    }
    public Attendee toDomain(AttendeeDto attendeeDto){
       Meeting meeting = meetingRepository.findById(attendeeDto.meetingId()).orElseThrow(()->new NotFoundException("Meeting not found with id: "+attendeeDto.meetingId()));
       User user = userRepository.findById(attendeeDto.userId()).orElseThrow(() -> new NotFoundException("User not found with id " + attendeeDto.userId()));
        return new Attendee(
               meeting,
                user
        );
    }
}
