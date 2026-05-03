package optis.guideme.api.dto.response;

import optis.guideme.domain.note.Visibility;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotesDto(UUID noteId, String title, Visibility visibility, LocalDateTime creationTime) {
}
