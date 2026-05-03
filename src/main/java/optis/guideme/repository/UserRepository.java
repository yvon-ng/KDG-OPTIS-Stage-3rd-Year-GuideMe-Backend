package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<@NonNull User, @NonNull UUID> {
    List<User> findAllByUserIdNot(UUID userId);
}
