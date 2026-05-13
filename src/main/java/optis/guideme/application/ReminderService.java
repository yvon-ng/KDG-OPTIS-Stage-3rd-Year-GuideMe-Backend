package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.ReminderMapper;
import optis.guideme.domain.meeting.Reminder;
import optis.guideme.repository.ReminderRepository;
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

    public void saveAll(List<Reminder> reminders) {
        reminderRepository.saveAll(reminders);
    }

    public List<ReminderDto> getReminders(UUID userId) {
        return reminderRepository.getAllByReceiver(userId).map(reminderMapper::toResponseDto).toList();
    }
}
