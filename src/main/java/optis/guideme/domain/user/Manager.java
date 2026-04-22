package optis.guideme.domain.user;

import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.meetingRequest.MeetingRequestId;
import optis.guideme.domain.meetingRequest.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Manager extends User {
    public void updateMeeting(){} // for adjusting a meeting
    public void updateMeetingRequest(){} // for rescheduling a meeting from a consultant
    public void updateConsultant(){} // for updating consultant details
    public Manager(UserId id, String firstName, String lastName, String email) {
        super(id,firstName, lastName, email);
    }

}

