package optis.guideme.api.dto.request;

import java.util.List;
import java.util.UUID;

public record NewNoteDto(String title, String description, String visibility, UUID meeting, List<UUID> sharedUsers) {
}
