package optis.guideme.application;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.api.mapper.request.NewMeetingRequestMapper;
import optis.guideme.repository.MeetingRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingRequestService {
    private final MeetingRequestRepository meetingRequestRepository;
    private final NewMeetingRequestMapper mapper;

    public MeetingRequestDto createMeetingRequest(NewMeetingRequestDto newMeetingRequestDto) {
        return mapper.toResponseDto(meetingRequestRepository.save(mapper.toDomain(newMeetingRequestDto)));
    }

    public List<MeetingRequestDto> getAllMeetingRequests(){
        return meetingRequestRepository.findAll().stream().map(
                mapper::toResponseDto
        ).toList();
    }
}


