package optis.guideme.api.dto.response;

import java.util.UUID;

public record NoteDto(UUID noteId, String title, String description, String visibility, UUID owner) {
}
