package optis.guideme.api.mapper;

import optis.guideme.api.dto.response.NotificationDto;
import optis.guideme.domain.Notification;
import optis.guideme.domain.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDto toResponseDto(Notification notification) {
        return new NotificationDto(
                notification.getNotificationId(),
                notification.getTitle(),
                notification.getDescription(),
                notification.getSentAt(),
                NotificationType.NOTIFICATION
        );
    }
}
