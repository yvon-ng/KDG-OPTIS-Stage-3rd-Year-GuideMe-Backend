package optis.guideme.application;

import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.mapper.request.NewAttendeeMapper;
import optis.guideme.api.mapper.request.NewMeetingMapper;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MeetingService {
private final MeetingRepository meetingRepository;
private final NewMeetingMapper mapper;
private final NewAttendeeMapper attendeeMapper;

    public MeetingService(MeetingRepository meetingRepository, NewMeetingMapper mapper, NewAttendeeMapper attendeeMapper) {
        this.meetingRepository = meetingRepository;
        this.mapper = mapper;
        this.attendeeMapper = attendeeMapper;
    }

    public MeetingDto createNewMeeting(NewMeetingDto newMeetingDto){
        List<Attendee> attendees = newMeetingDto.sharedUsers().stream().map(attendeeMapper::toDomain).toList();
        Meeting meeting = mapper.toDomain(newMeetingDto);
        meeting.validateMinimumRoles(attendees);
        return mapper.toResponseDto(meetingRepository.save(meeting));
    }
}
