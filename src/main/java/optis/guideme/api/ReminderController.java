package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.ReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reminders")
public class ReminderController {
    private final ReminderService reminderService;
    private final CurrentUserProvider currentUserProvider;
    private final Logger log = LoggerFactory.getLogger(ReminderController.class);

    @GetMapping
    public List<ReminderDto> getReminder() {
        return reminderService.getReminders(currentUserProvider.getUserId());
    }

    @MessageMapping("/test")
    public void test(Principal principal) {
        log.info("WS Principal: {}", principal.getName());
    }
}
