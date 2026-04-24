package optis.guideme.application;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.api.mapper.request.NewMeetingRequestMapper;
import optis.guideme.repository.MeetingRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class MeetingRequestService {
    private final MeetingRequestRepository meetingRequestRepository;
    private final NewMeetingRequestMapper mapper;

    public MeetingRequestService(MeetingRequestRepository meetingRequestRepository, NewMeetingRequestMapper mapper) {
        this.meetingRequestRepository = meetingRequestRepository;
        this.mapper = mapper;
    }

    public MeetingRequestDto createMeetingRequest(NewMeetingRequestDto newMeetingRequestDto) {
        return mapper.toResponseDto(meetingRequestRepository.save(mapper.toDomain(newMeetingRequestDto)));
    }
}


