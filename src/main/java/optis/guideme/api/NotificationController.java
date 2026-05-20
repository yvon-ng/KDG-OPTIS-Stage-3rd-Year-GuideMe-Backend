package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.application.NotificationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
}
