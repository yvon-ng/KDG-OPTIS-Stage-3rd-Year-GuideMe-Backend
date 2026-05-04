package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.application.MeetingRequestService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/meeting-requests")
public class MeetingRequestController {
    private final MeetingRequestService meetingRequestService;

    @PostMapping
    public MeetingRequestDto createMeetingRequest(@AuthenticationPrincipal Jwt jwt, @RequestBody NewMeetingRequestDto newMeetingRequestDto) {
       UUID userId = UUID.fromString(jwt.getClaim("oid"));
       return meetingRequestService.createMeetingRequest(userId, newMeetingRequestDto);
    }

    @GetMapping
    public List<MeetingRequestDto> getMeetingRequests() {
        return meetingRequestService.getAllMeetingRequests();
    }

    @GetMapping("me/incoming")
    public List<MeetingRequestDto> getIncomingMeetingRequests(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return meetingRequestService.getIncomingMeetingRequests(userId);
    }

    @GetMapping("me/outgoing")
    public List<MeetingRequestDto> getOutgoingMeetingRequests(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return meetingRequestService.getOutgoingMeetingRequests(userId);
    }

    @PostMapping("/accept{meetingRequestId}")
    public MeetingDto acceptMeetingRequest(@PathVariable UUID meetingRequestId) {
        return meetingRequestService.acceptRequest(meetingRequestId);
    }
}
