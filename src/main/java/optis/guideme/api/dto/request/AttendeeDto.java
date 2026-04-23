package optis.guideme.api.dto.request;

import optis.guideme.domain.meeting.Attendee;

import java.util.UUID;

public record AttendeeDto (UUID userId, UUID meetingId) {
}
