package optis.guideme.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

public record NewMeetingDto(@NotNull String name, @NotNull Instant startDateTime, @NotNull List<NewAttendeeDto> attendees, @NotNull Instant endDateTime) {

}
