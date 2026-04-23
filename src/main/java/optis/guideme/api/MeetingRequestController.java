package optis.guideme.api;

import optis.guideme.api.dto.request.MeetingRequestDto;
import optis.guideme.application.MeetingRequestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/meeting-requests")
public class MeetingRequestController {
    private final MeetingRequestService meetingRequestService;

    public MeetingRequestController(MeetingRequestService meetingRequestService) {
        this.meetingRequestService = meetingRequestService;
    }

    @PostMapping("requests")
    public MeetingRequestDto createMeetingRequest(@RequestBody MeetingRequestDto meetingRequestDto) {
       return meetingRequestService.createMeetingRequest(meetingRequestDto);
    }
}
