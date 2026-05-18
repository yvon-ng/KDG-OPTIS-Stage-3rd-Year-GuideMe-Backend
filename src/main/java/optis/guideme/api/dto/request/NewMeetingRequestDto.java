package optis.guideme.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


public record NewMeetingRequestDto(@NotNull String name, @NotNull Instant startDateTime, @NotNull Instant endDateTime, int recurrence, @NotNull List<UUID> requestFor, UUID previousRequestId) {
}
        