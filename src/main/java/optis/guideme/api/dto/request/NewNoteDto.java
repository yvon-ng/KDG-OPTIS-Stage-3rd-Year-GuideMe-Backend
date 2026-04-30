package optis.guideme.api.dto.request;

import optis.guideme.domain.note.Visibility;

import java.util.List;
import java.util.UUID;

public record NewNoteDto(String title, String description, Visibility visibility, UUID meeting, List<UUID> sharedUsers) {
}
