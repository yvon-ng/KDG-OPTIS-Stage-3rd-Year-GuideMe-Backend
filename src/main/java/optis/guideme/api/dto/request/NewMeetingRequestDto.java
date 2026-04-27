package optis.guideme.api.dto.request;

import lombok.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//TODO: for now UUID for requestedBy directly, later via security: get currentUser.id() or something
public record NewMeetingRequestDto(@NonNull String name, LocalDateTime startDateTime, @Nullable LocalDateTime endDateTime, int recurrence, List<UUID> requestFor, @Nullable UUID previousRequestId, UUID requestedBy) {

}
