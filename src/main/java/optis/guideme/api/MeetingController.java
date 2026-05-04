package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.application.MeetingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping
    public MeetingDto createNewMeeting(@RequestBody NewMeetingDto newMeetingDto) {
        return meetingService.createNewMeeting(newMeetingDto);
    }

    @GetMapping
    public List<MeetingDto> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("myMeetings")
    public List<MeetingDto> getMyMeetings(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return meetingService.getAllMeetingsByUser(userId);
    }

    @GetMapping("/attendees/{meetingId}")
    public List<AttendeeDto> getMeetingAttendees(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID meetingId) {
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return meetingService.getMeetingAttendees(userId, meetingId);
    }
}
