package optis.guideme.api;

import lombok.NonNull;
import optis.guideme.api.dto.MeetingRequestDto;
import optis.guideme.application.MeetingRequestService;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/meeting-requests")
public class MeetingRequestController {
    private final MeetingRequestService meetingRequestService;

    public MeetingRequestController(MeetingRequestService meetingRequestService) {
        this.meetingRequestService = meetingRequestService;
    }

    @PostMapping("/requests")
    public ResponseEntity<@NonNull MeetingRequestDto> sendMeetingRequest(@RequestBody MeetingRequestDto meetingRequestDto) {
        MeetingRequest meetingRequest = meetingRequestService.createMeetingRequest(meetingRequestDto);
        return ResponseEntity.ok(MeetingRequestDto.fromDomain(meetingRequest));
    }
}
