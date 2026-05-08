package optis.guideme.api.dto;

import com.fasterxml.jackson.databind.JsonNode;
import optis.guideme.domain.note.Visibility;

import java.util.List;
import java.util.UUID;

public record UpdateNoteDto(String title, JsonNode description, Visibility visibility, UUID meetingId, List<UUID> sharedUsers) {

}
