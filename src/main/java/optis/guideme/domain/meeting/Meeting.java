package optis.guideme.domain.meeting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    // when created make reminder for all attendee
    private MeetingId meetingId;
    private String name; //should be same as meetingrequest
    private String referenceCode; //should be same as meetingrequest
    private LocalDateTime startDateTime;
    private List<Attendee> sharedUsers =  new ArrayList<>(); // atleast two; 1 manager 1 consultant !
    private LocalDateTime endDateTime;
    private boolean occured;

    public Meeting(MeetingId id, LocalDateTime endDateTime, String name, boolean occured,  String referenceCode, List<Attendee> sharedUsers, LocalDateTime startDateTime) {
        this.meetingId = id;
        this.endDateTime = endDateTime;
        this.name = name;
        this.occured = occured;
        this.referenceCode = referenceCode;
        this.sharedUsers = sharedUsers;
        this.startDateTime = startDateTime;
    }

    public Quarter getQuarter() {
        return Quarter.fromMonth(startDateTime.getMonthValue());
    }

    public String getDisplayName() {
        return (name != null && !name.isBlank()) ? name : referenceCode;
    }

////    //add attendee to meeting
//    public void addAttendee(Attendee attendee) {
//        this.sharedUsers.add(attendee);
//    }
}
