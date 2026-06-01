package optis.guideme.api.dto.response;

import optis.guideme.domain.note.Visibility;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NotePreviewDto(UUID noteId, String title, Visibility visibility, LocalDateTime creationTime, List<UUID> sharedUsers) {
}
