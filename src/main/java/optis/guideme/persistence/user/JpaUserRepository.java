package optis.guideme.persistence.user;

import lombok.NonNull;
import optis.guideme.domain.user.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<@NonNull JpaUserEntity, @NonNull UUID> {
    boolean existsByUserIdAndRole(UUID userId, Role role);

}
