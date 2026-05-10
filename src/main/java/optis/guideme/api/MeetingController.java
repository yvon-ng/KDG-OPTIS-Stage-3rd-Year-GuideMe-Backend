package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
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
        return meetingService.createNewMeeting(newMeetingDto);
    }

    @GetMapping
    public List<MeetingDto> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("me/next")
    public MeetingDto getNextMeeting() {
        return meetingService.getNextMeeting();
    }

    @GetMapping("my-meetings")
    public List<MeetingDto> getMyMeetings() {
        return meetingService.getAllMeetingsByUser(currentUserProvider.getUserId());
    }

    @GetMapping("attendees/{meetingId}")
    public List<AttendeeDto> getMeetingAttendees(@PathVariable UUID meetingId) {
        return meetingService.getMeetingAttendees(currentUserProvider.getUserId(), meetingId);
    }
}
