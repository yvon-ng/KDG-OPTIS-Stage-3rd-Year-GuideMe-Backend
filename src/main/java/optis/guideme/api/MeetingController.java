package optis.guideme.api;

import lombok.NonNull;
import optis.guideme.api.dto.request.MeetingDto;
import optis.guideme.api.mapper.MeetingMapper;
import optis.guideme.application.MeetingService;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public MeetingDto createNewMeeting(@RequestBody MeetingDto meetingDto) {
        return meetingService.createNewMeeting(meetingDto);
    }
}
