package optis.guideme.api.dto.response;

import optis.guideme.domain.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDto(UUID id, String title, String description, LocalDateTime sentAt, NotificationType type) {

}
