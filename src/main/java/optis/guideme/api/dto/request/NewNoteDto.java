package optis.guideme.api.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import optis.guideme.domain.note.Visibility;

import java.util.List;
import java.util.UUID;

public record NewNoteDto(String title, JsonNode description, Visibility visibility, UUID meetingId, List<UUID> sharedUsers) {
}
