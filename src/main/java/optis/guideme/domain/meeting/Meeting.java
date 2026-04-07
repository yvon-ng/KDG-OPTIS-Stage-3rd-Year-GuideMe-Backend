package optis.guideme.domain.meeting;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    // when created make reminder for all attendee
    private MeetingId meetingId;
    private String name; //should be same as meetingrequest
    private String referenceCode; //should be same as meetingrequest
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<Attendee> attendees; // atleast two 1 manager 1 consultant !
    private boolean occured;

    public Quarter getQuarter() {
        return Quarter.fromMonth(startDateTime.getMonthValue());
    }

    public String getDisplayName() {
        return (name != null && !name.isBlank()) ? name : referenceCode;
    }
}
