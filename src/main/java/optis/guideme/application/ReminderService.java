package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.domain.meeting.Reminder;
import optis.guideme.repository.ReminderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;

    public void saveAll(List<Reminder> reminders) {
        reminderRepository.saveAll(reminders);
    }
}
