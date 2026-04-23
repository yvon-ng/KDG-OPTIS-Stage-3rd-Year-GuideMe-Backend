package optis.guideme.application;

import optis.guideme.api.dto.request.AttendeeDto;
import optis.guideme.api.dto.request.MeetingDto;
import optis.guideme.api.mapper.MeetingMapper;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MeetingService {
private final MeetingRepository meetingRepository;
private final MeetingMapper mapper;

    public MeetingService(MeetingRepository meetingRepository, MeetingMapper mapper) {
        this.meetingRepository = meetingRepository;
        this.mapper = mapper;
    }

    public MeetingDto createNewMeeting(MeetingDto meetingDto){
        return mapper.toDto(meetingRepository.save(mapper.toDomain(meetingDto)));
    }
}
