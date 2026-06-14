package optis.guideme.api.dto.response;

import optis.guideme.domain.note.Visibility;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NotePreviewDto(UUID noteId, String title, Visibility visibility, UUID owner, LocalDateTime creationTime, List<UUID> sharedUsers) {
}
