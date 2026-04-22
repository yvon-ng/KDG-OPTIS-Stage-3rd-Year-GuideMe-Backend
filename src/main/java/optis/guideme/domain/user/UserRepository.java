package optis.guideme.domain.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> getUser(UserId userId);
}
