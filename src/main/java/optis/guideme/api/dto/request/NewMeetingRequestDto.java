package optis.guideme.api.dto.request;

import lombok.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public record NewMeetingRequestDto(@NonNull String name, LocalDateTime startDateTime, @Nullable LocalDateTime endDateTime, int recurrence, List<UUID> requestFor, @Nullable UUID previousRequestId) {

}
