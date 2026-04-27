package optis.guideme.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record MeetingDto (String name, String referenceCode, LocalDateTime startDateTime, List<AttendeeDto> attendees, boolean occurred){
}
