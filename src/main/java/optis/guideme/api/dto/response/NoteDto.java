package optis.guideme.api.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import optis.guideme.domain.note.Visibility;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NoteDto(UUID noteId, String title, JsonNode description, Visibility visibility, UUID owner, LocalDateTime creationTime    , UUID meetingId, List<UUID> sharedUsers) {
}
