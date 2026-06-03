package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.NotificationDto;
import optis.guideme.application.NotificationService;
import optis.guideme.configs.CurrentUserProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDto> getNotifications() {
        return notificationService.getSentNotifications(CurrentUserProvider.getUserId());
    }

    @PutMapping("{notificationId}")
    public void readNotification(@PathVariable UUID notificationId) {
        notificationService.markNotificationRead(notificationId);
    }
}
