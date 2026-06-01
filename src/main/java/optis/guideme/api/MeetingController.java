package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.api.dto.response.MeetingContextDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.configs.CurrentUserProvider;
import optis.guideme.application.MeetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping
    public MeetingDto createNewMeeting(@RequestBody NewMeetingDto newMeetingDto) {
        return meetingService.createNewMeeting(CurrentUserProvider.getUserId(), newMeetingDto);
    }

    @GetMapping
    public List<MeetingDto> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("me/context")
    public MeetingContextDto getNextAndLastMeeting() {
        return meetingService.getNextAndLastMeeting(CurrentUserProvider.getUserId());
    }

    @GetMapping("my-meetings")
    public List<MeetingDto> getMyMeetings(@RequestParam(required = false) Integer quarter, @RequestParam(required = false) Integer year, @RequestParam(required = false) List<UUID> personIds) {
        return meetingService.getAllMeetingsByUser(CurrentUserProvider.getUserId(), quarter, year, personIds);
    }

    @GetMapping("attendees/{meetingId}")
    public List<AttendeeDto> getMeetingAttendees(@PathVariable UUID meetingId) {
        return meetingService.getMeetingAttendees(CurrentUserProvider.getUserId(), meetingId);
    }

    @GetMapping("/{meetingId}")
    public MeetingDto getMeeting(@PathVariable UUID meetingId) {
        return meetingService.get(CurrentUserProvider.getUserId(), meetingId);
    }
    @GetMapping("/consultant-meetings")
    public Map<UUID, List<MeetingDto>> getAllMeetingsPerConsultant() {
        return meetingService.getAllMeetingsPerConsultant();
    }

    @PatchMapping("/{meetingId}/occurred")
    public void markAsOccurred(@PathVariable UUID meetingId) {
        meetingService.markMeetingAsOccurred(meetingId);
    }
}
