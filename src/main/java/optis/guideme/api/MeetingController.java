package optis.guideme.api;

import lombok.NonNull;
import optis.guideme.api.dto.MeetingDto;
import optis.guideme.application.MeetingService;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<@NonNull MeetingDto> createNewMeeting(@RequestBody MeetingDto meetingDto) {
        Meeting meeting = meetingService.createNewMeeting(meetingDto);
        return ResponseEntity.ok(MeetingDto.fromDomain(meeting));
    }
}
