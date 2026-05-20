package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.api.dto.response.MeetingContextDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.MeetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/meetings")
public class MeetingController {
    private final MeetingService meetingService;
    private final CurrentUserProvider currentUserProvider;

    @PostMapping
    public MeetingDto createNewMeeting(@RequestBody NewMeetingDto newMeetingDto) {
        return meetingService.createNewMeeting(currentUserProvider.getUserId(), newMeetingDto);
    }

    @GetMapping
    public List<MeetingDto> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("me/context")
    public MeetingContextDto getNextAndLastMeeting() {
        return meetingService.getNextAndLastMeeting(currentUserProvider.getUserId());
    }

    @GetMapping("my-meetings")
    public List<MeetingDto> getMyMeetings(@RequestParam(required = false) Integer quarter, @RequestParam(required = false) Integer year, @RequestParam(required = false) List<UUID> personIds) {
        return meetingService.getAllMeetingsByUser(currentUserProvider.getUserId(), quarter, year, personIds);
    }

    @GetMapping("attendees/{meetingId}")
    public List<AttendeeDto> getMeetingAttendees(@PathVariable UUID meetingId) {
        return meetingService.getMeetingAttendees(currentUserProvider.getUserId(), meetingId);
    }

    @GetMapping("/{meetingId}")
    public MeetingDto getMeeting(@PathVariable UUID meetingId) {
        return meetingService.get(currentUserProvider.getUserId(), meetingId);
    }
}
