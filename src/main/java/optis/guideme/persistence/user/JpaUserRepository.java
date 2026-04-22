package optis.guideme.persistence.user;

import lombok.NonNull;
import optis.guideme.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<@NonNull JpaUserEntity, @NonNull UUID> {
    boolean existsByUserIdAndRole(UUID userId, Role role);
}