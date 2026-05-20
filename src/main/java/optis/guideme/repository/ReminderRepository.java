package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meeting.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface ReminderRepository extends JpaRepository<@NonNull Reminder, @NonNull UUID> {
    @Query("""
            SELECT r FROM Reminder r
            WHERE r.scheduledAt BETWEEN :start AND :end
            AND r.sentAt IS NULL
            """)
    Stream<Reminder> findRemindersToSend(LocalDateTime start, LocalDateTime end);

    Stream<Reminder> findAllByReceiverAndSentAtIsNotNull(UUID userId);
}
