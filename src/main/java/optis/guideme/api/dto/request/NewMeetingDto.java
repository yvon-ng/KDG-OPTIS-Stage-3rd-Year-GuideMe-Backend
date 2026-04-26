package optis.guideme.api.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record NewMeetingDto(String name, LocalDateTime startDateTime, List<NewAttendeeDto> attendees, LocalDateTime endDateTime) {

}
