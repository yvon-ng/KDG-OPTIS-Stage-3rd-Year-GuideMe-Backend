package optis.guideme.api;

import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.application.MeetingRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/meeting-requests")
public class MeetingRequestController {
    private final MeetingRequestService meetingRequestService;

    public MeetingRequestController(MeetingRequestService meetingRequestService) {
        this.meetingRequestService = meetingRequestService;
    }

    @PostMapping("requests")
    public MeetingRequestDto createMeetingRequest(@RequestBody NewMeetingRequestDto newMeetingRequestDto) {
       return meetingRequestService.createMeetingRequest(newMeetingRequestDto);
    }

    @GetMapping("requests")
    public List<MeetingRequestDto> getMeetingRequests() {
        return meetingRequestService.getAllMeetingRequests();
    }
}
