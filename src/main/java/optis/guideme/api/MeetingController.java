package optis.guideme.api;

import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.application.MeetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public MeetingDto createNewMeeting(@RequestBody NewMeetingDto newMeetingDto) {
        return meetingService.createNewMeeting(newMeetingDto);
    }

    @GetMapping
    public List<MeetingDto> getAllMeetings() {
        return meetingService.getAllMeetings();
    }
}
