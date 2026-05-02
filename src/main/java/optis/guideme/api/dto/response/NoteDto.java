package optis.guideme.api.dto.response;

import optis.guideme.domain.note.Visibility;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoteDto(UUID noteId, String title, String description, Visibility visibility, UUID owner, LocalDateTime creationTime) {
}
