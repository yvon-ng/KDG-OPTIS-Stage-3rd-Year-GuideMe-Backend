package optis.guideme.application;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.api.mapper.request.NewMeetingRequestMapper;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MeetingRequestService {
    private final MeetingRequestRepository meetingRequestRepository;
    private final NewMeetingRequestMapper mapper;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final UserService userService;

    public MeetingRequestDto createMeetingRequest(UUID userId, NewMeetingRequestDto newMeetingRequestDto) {
        MeetingRequest meetingRequest = mapper.toDomain(newMeetingRequestDto);
        meetingRequest.assignRequestedBy(userId);
        List<User> requestFor = userService.getUsersByIds(newMeetingRequestDto.requestFor());
        meetingRequest.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest, requestFor));
        return mapper.toResponseDto(meetingRequestRepository.save(meetingRequest));
    }

    public List<MeetingRequestDto> getAllMeetingRequests(){
        return meetingRequestRepository.findAll().stream().map(
                mapper::toResponseDto
        ).toList();
    }

}


