package optis.guideme.api;

import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.application.MeetingRequestService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/meeting-requests")
public class MeetingRequestController {
    private final MeetingRequestService meetingRequestService;

    public MeetingRequestController(MeetingRequestService meetingRequestService) {
        this.meetingRequestService = meetingRequestService;
    }

    @PostMapping
    public MeetingRequestDto createMeetingRequest(@AuthenticationPrincipal Jwt jwt, @RequestBody NewMeetingRequestDto newMeetingRequestDto) {
       UUID userId = UUID.fromString(jwt.getClaim("oid"));
       return meetingRequestService.createMeetingRequest(userId, newMeetingRequestDto);
    }

    @GetMapping
    public List<MeetingRequestDto> getMeetingRequests() {
        return meetingRequestService.getAllMeetingRequests();
    }
}
