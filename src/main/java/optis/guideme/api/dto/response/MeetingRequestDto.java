package optis.guideme.api.dto.response;

import lombok.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MeetingRequestDto(UUID meetingRequestId, @NonNull String name, String referenceCode, LocalDateTime startDateTime, @Nullable LocalDateTime endDateTime, int recurrence, List<UUID> requestFor, @Nullable UUID previousRequestId, UUID requestedBy) {
}
