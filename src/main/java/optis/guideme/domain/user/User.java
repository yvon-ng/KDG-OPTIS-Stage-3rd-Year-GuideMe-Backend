package optis.guideme.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.meetingRequest.MeetingRequestId;
import optis.guideme.domain.meetingRequest.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class User {
    private UserId id;
    private String firstName;
    private String lastName;
    private String email;

//    private List<Goal> goals;
//    private List<Reminder> reminders;
}
