package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.api.mapper.ReminderMapper;
import optis.guideme.domain.Notification;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Reminder;
import optis.guideme.repository.ReminderRepository;
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

    public void saveAll(List<Reminder> reminders) {
        reminderRepository.saveAll(reminders);
    }

    public List<ReminderDto> getSentReminders(UUID userId) {
        return reminderRepository.findAllByReceiverAndSentAtIsNotNull(userId).map(reminderMapper::toResponseDto).toList();
    }

    public void sendReminder(Reminder reminder) {
        reminder.markAsSent();
        reminderRepository.save(reminder);
        ReminderDto dto = reminderMapper.toResponseDto(reminder);
        messagingTemplate.convertAndSendToUser(
                reminder.getReceiver().toString(),
                "/queue/notifications",
                dto);
    }

    public List<Reminder> getRemindersToSend() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMinuteAgo = now.plusMinutes(2);
        return reminderRepository.findRemindersToSend(now, oneMinuteAgo).toList();
    }

    public void markReminderRead(UUID reminderId) {
        Reminder reminder = getReminder(reminderId);
        reminder.markAsRead();
        reminderRepository.save(reminder);
    }

    private Reminder getReminder(UUID reminderId) {
        return reminderRepository.findById(reminderId).orElseThrow(() -> new NotFoundException("Reminder Not Found with Id: " + reminderId));
    }
}
