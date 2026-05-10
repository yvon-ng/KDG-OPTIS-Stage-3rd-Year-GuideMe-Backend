package optis.guideme.api;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.api.dto.response.MeetingContextDto;
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

    @GetMapping("me/context")
    public MeetingContextDto getNextAndLastMeeting(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return meetingService.getNextAndLastMeeting(userId);
    }

    @GetMapping("myMeetings")
    public List<MeetingDto> getMyMeetings(@AuthenticationPrincipal Jwt jwt,
                                          @RequestParam(required = false) Integer quarter,
                                          @RequestParam(required = false) Integer year,
                                          @RequestParam(required = false) UUID personId) {
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return meetingService.getAllMeetingsByUser(userId,
                                                    quarter,
                                                    year,
                                                    personId);
    }

    @GetMapping("/attendees/{meetingId}")
    public List<AttendeeDto> getMeetingAttendees(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID meetingId) {
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return meetingService.getMeetingAttendees(userId, meetingId);
    }

    @PostMapping("/create")
    public MeetingDto createMeeting(@RequestBody NewMeetingDto newMeetingDto) {
        return meetingService.createNewMeeting(newMeetingDto);
    }

}
