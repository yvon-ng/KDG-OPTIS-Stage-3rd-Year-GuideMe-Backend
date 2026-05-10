package optis.guideme.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record NewMeetingDto(@NotNull String name,@NotNull LocalDateTime startDateTime,@NotNull List<NewAttendeeDto> attendees,@NotNull LocalDateTime endDateTime) {

}
