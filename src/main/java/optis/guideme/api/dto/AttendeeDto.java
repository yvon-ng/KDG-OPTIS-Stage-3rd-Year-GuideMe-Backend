package optis.guideme.api.dto;

import optis.guideme.domain.meeting.MeetingId;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.user.UserId;

import java.util.UUID;

public record AttendeeDto (UUID userId, UUID meetingId) {
    public static Attendee toDomain(AttendeeDto attendeeDto) {
        Attendee attendee = new Attendee(
                UserId.of(attendeeDto.userId()),
                MeetingId.of(attendeeDto.meetingId())
        );
        return attendee;
    }
}
