package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.ReminderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reminders")
public class ReminderController {
    private final ReminderService reminderService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping
    public List<ReminderDto> getReminder() {
        return reminderService.getReminders(currentUserProvider.getUserId());
    }
}
