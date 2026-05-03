package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.application.MeetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("me/next")
    public MeetingDto getNextMeeting() {
        return meetingService.getNextMeeting();
    }
}
