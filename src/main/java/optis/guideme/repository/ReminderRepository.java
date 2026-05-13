package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meeting.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface ReminderRepository extends JpaRepository<@NonNull Reminder, @NonNull UUID> {
    Stream<Reminder> getAllByReceiver(UUID receiver);
}
