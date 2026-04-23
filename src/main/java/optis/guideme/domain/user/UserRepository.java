package optis.guideme.domain.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {

    User findById(UUID uuid);

    boolean existsByUserIdAndRole(UUID uuid, Role role);
}
