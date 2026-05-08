package optis.guideme.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public record NewMeetingRequestDto(@NotNull String name, @NotNull LocalDateTime startDateTime, @NotNull LocalDateTime endDateTime, @NotNull int recurrence, @NotNull List<UUID> requestFor, UUID previousRequestId) {
}
