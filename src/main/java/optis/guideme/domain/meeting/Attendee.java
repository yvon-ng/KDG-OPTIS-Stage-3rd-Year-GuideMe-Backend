package optis.guideme.domain.meeting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import optis.guideme.domain.user.UserId;

@Getter
@AllArgsConstructor
public class Attendee {
    private UserId userId;
    private MeetingId meetingId;
}
