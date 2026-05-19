package optis.guideme.configs;

import lombok.RequiredArgsConstructor;
import optis.guideme.application.ReminderService;
import optis.guideme.domain.meeting.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReminderScheduler {
    private final ReminderService reminderService;
    private final Logger log = LoggerFactory.getLogger(ReminderScheduler.class.getName());

    // Run every minute to check for reminders that need to be sent
    @Scheduled(fixedRate = 60000) // 60 seconds
    public void sendDueReminders() {
        // Find reminders where scheduledAt is within the next minute
        List<Reminder> dueReminders = reminderService.getRemindersToSend();

        dueReminders.forEach(reminder -> {
            try {
                reminderService.sendReminder(reminder);
                log.info("Successfully sent reminder: {}", reminder.getReminderId());
            } catch (Exception e) {
                log.error("Failed to send reminder: {}", reminder.getReminderId(), e);
            }
        });
    }
}