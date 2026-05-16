package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.MeetingRequestService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/meeting-requests")
public class MeetingRequestController {
    private final MeetingRequestService meetingRequestService;
    private final CurrentUserProvider currentUserProvider;

    @PostMapping
    public MeetingRequestDto createMeetingRequest(@RequestBody NewMeetingRequestDto newMeetingRequestDto) {
       return meetingRequestService.createMeetingRequest(currentUserProvider.getUserId(), newMeetingRequestDto);
    }

    @GetMapping
    public List<MeetingRequestDto> getMeetingRequests() {
        return meetingRequestService.getAllMeetingRequests();
    }

    @GetMapping("me/incoming")
    public List<MeetingRequestDto> getIncomingMeetingRequests() {
        return meetingRequestService.getIncomingMeetingRequests(currentUserProvider.getUserId());
    }

    @GetMapping("me/outgoing")
    public List<MeetingRequestDto> getOutgoingMeetingRequests() {
        return meetingRequestService.getOutgoingMeetingRequests(currentUserProvider.getUserId());
    }

    @PostMapping("/accept/{meetingRequestId}")
    public MeetingDto acceptMeetingRequest(@PathVariable UUID meetingRequestId) {
        return meetingRequestService.acceptRequest(meetingRequestId);
    }

    @PostMapping("/decline/{meetingRequestId}")
    public void declineMeetingRequest(@PathVariable UUID meetingRequestId) {
        meetingRequestService.declineRequest(meetingRequestId);
    }
}
