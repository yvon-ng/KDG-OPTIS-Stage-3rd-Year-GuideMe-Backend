package optis.guideme.api.dto.response;

import java.util.UUID;

public record NoteDto(String noteId, String title, String description, String visibility, UUID owner) {
}
