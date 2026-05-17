package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<@NonNull User, @NonNull UUID> {
    Stream<User> findAllByUserIdNot(UUID userId);

    Stream<User> getAll();
}
