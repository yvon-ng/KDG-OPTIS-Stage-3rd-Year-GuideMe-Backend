package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.api.mapper.ReminderMapper;
import optis.guideme.domain.meeting.Reminder;
import optis.guideme.repository.ReminderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final Logger log = LoggerFactory.getLogger(ReminderService.class.getName());

    public void saveAll(List<Reminder> reminders) {
        reminderRepository.saveAll(reminders);
    }

    public List<ReminderDto> getSentReminders(UUID userId) {
        return reminderRepository.getAllByReceiverAndSentIsTrue(userId).map(reminderMapper::toResponseDto).toList();
    }

    public void sendReminder(Reminder reminder) {
        log.info("Sending reminder for user: {}", reminder.getReceiver());
        ReminderDto dto = reminderMapper.toResponseDto(reminder);
        messagingTemplate.convertAndSendToUser(
                reminder.getReceiver().toString(),
                "/queue/reminders",
                dto);
        reminder.markAsSent();
        reminderRepository.save(reminder);
    }

    public List<Reminder> getRemindersToSend() {
        LocalDateTime twoMinFromNow = LocalDateTime.now().plusHours(Reminder.TEST_EMAIL_REMINDER_MINUTES_BEFORE_MEETING);
        return reminderRepository.findRemindersToSend(twoMinFromNow).toList();
    }
}
