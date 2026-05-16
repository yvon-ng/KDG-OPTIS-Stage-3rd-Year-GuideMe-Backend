package optis.guideme.application;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.api.mapper.MeetingMapper;
import optis.guideme.api.mapper.MeetingRequestMapper;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;




@Service
@RequiredArgsConstructor
@Transactional
public class MeetingRequestService {
    private final MeetingRequestRepository meetingRequestRepository;
    private final MeetingRequestMapper mapper;
    private final MeetingMapper meetingMapper;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final UserService userService;
    private final MeetingService meetingService;

    private MeetingRequest get(UUID meetingRequestId){
        return meetingRequestRepository.findById(meetingRequestId).orElseThrow();
    }

    public MeetingRequestDto createMeetingRequest(UUID userId, NewMeetingRequestDto dto) {
        List<User> requestFor = userService.getUsersEntitiesByIds(dto.requestFor());

        if (dto.previousRequestId() != null) {
            MeetingRequest previous = get(dto.previousRequestId());
            LocalDateTime newStart = LocalDateTime.parse(dto.startDateTime());
            LocalDateTime newEnd = LocalDateTime.parse(dto.endDateTime());

            MeetingRequest rescheduled = previous.reschedule(newStart, newEnd, dto.recurrencePerYear(), userId, dto.requestFor());

            return mapper.toResponseDto(meetingRequestRepository.save(rescheduled));
        }

        MeetingRequest meetingRequest = mapper.toDomain(dto);
        meetingRequest.assignRequestedBy(userId);
        meetingRequest.assignReferenceCode(
                defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest, requestFor)
        );
        return mapper.toResponseDto(meetingRequestRepository.save(meetingRequest));
    }

    public List<MeetingRequestDto> getAllMeetingRequests(){
        return meetingRequestRepository.findAll().stream().map(
                mapper::toResponseDto
        ).toList();
    }

    public List<MeetingRequestDto> getIncomingMeetingRequests(UUID userId) {
        return meetingRequestRepository.getIncomingMeetingRequests(userId).stream().map(
                mapper::toResponseDto
        ).toList();
    }

    public List<MeetingRequestDto> getOutgoingMeetingRequests(UUID userId) {
        return meetingRequestRepository.getOutgoingMeetingRequests(userId).stream().map(
        mapper::toResponseDto).toList();
    }

    public MeetingDto acceptRequest(UUID meetingRequestId) {
        MeetingRequest meetingRequest = meetingRequestRepository.findById(meetingRequestId).orElseThrow();
        User requestedByUser = userService.get(meetingRequest.getRequestedBy());
        List<User> requestedForUsers = userService.getUsersEntitiesByIds(meetingRequest.getRequestFor());
        List<Attendee> attendees = new ArrayList<>();
        attendees.add(new Attendee(meetingRequest.getRequestedBy(), requestedByUser.getRole()));
        attendees.addAll(requestedForUsers.stream().map(user -> new Attendee(user.getUserId(), user.getRole())).toList());
        Meeting meeting = meetingRequest.accept(attendees);
        meetingService.save(meeting);
        return meetingMapper.toResponseDto(meeting);
    }

    public void declineRequest(UUID meetingRequestId) {
         get(meetingRequestId).decline();
    }
}


