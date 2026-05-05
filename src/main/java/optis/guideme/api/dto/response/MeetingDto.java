package optis.guideme.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MeetingDto(UUID meetingId, String name, String referenceCode, LocalDateTime startDateTime, List<AttendeeDto> attendees, LocalDateTime endDateTime, boolean occurred){
}
