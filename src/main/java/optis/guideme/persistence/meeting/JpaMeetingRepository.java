package optis.guideme.persistence.meeting;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaMeetingRepository extends JpaRepository<@NonNull JpaMeetingEntity, @NonNull UUID> {

}
