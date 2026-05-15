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
        log.info("Saving {} reminders", reminders.size());
        reminderRepository.saveAll(reminders);
        reminders.forEach(reminder -> {
            log.info("Processing reminder for user: {}, receiver type: {}", reminder.getReceiver(), reminder.getReceiver().getClass());

            ReminderDto dto = reminderMapper.toResponseDto(reminder);
            messagingTemplate.convertAndSendToUser(
                    reminder.getReceiver().toString(),
                    "/queue/reminders",
                    dto);
            log.info("Reminder sent via WebSocket");
        });
    }

    public List<ReminderDto> getReminders(UUID userId) {
        return reminderRepository.getAllByReceiver(userId).map(reminderMapper::toResponseDto).toList();
    }
}
