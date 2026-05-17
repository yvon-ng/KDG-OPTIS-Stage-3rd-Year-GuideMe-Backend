package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface GoalRepository extends JpaRepository<@NonNull Goal, @NonNull UUID> {
    Stream<Goal> findAllByUserIdOrderByCreationDateDesc(UUID userId);

    Stream<Goal> findAllByUserIdAndAchievementDateIsNotNullOrderByCreationDateDesc(UUID userId);
}
