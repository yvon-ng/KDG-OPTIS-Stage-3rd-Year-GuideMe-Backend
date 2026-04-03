package optis.guideme.domain.meeting;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    // when created make reminder for all attendee
    private MeetingId meetingId;
    private String referenceCode;
    private LocalDateTime startDateTime;
    private List<Attendee> sharedUsers; // atleast two 1 manager 1 consultant !
}
