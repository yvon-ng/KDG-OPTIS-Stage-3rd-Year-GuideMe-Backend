package optis.guideme.persistence.meeting.attendee;

import jakarta.persistence.Embeddable;
import optis.guideme.domain.meeting.MeetingId;
import optis.guideme.domain.user.UserId;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AttendeeId implements Serializable {

    private UUID userId;
    private UUID meetingId;


    public AttendeeId() {}

    public AttendeeId(UUID meetingId, UUID userId) {
        this.meetingId = meetingId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendeeId)) return false;
        AttendeeId that = (AttendeeId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(meetingId, that.meetingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, meetingId);
    }
}