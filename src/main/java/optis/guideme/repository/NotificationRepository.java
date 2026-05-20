package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<@NonNull Notification, @NonNull UUID> {
}
