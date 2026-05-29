package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.NotificationDto;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.application.NotificationService;
import optis.guideme.configs.CurrentUserProvider;
import optis.guideme.domain.Notification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDto> getReminder() {
        return notificationService.getSentNotifications(CurrentUserProvider.getUserId());
    }
}
