package optis.guideme.api.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import optis.guideme.domain.note.Visibility;

import java.util.List;
import java.util.UUID;

public record UpdateNoteDto(@NotNull String title, @NotNull JsonNode description, @NotNull Visibility visibility, UUID meetingId, List<UUID> sharedUsers) {
}
