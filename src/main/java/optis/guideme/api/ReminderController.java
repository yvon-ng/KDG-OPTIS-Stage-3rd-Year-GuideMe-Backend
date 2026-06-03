package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.configs.CurrentUserProvider;
import optis.guideme.application.ReminderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reminders")
public class ReminderController {
    private final ReminderService reminderService;

    @GetMapping
    public List<ReminderDto> getReminder() {
        return reminderService.getSentReminders(CurrentUserProvider.getUserId());
    }

    @PutMapping("{reminderId}")
    public void readReminder(@PathVariable UUID reminderId) {
        reminderService.markReminderRead(reminderId);
    }
}
