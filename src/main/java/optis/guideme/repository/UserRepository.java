package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<@NonNull User, @NonNull UUID> {
    Stream<User> findAllByUserIdNot(UUID userId);

    @Query("SELECT u FROM User u WHERE TYPE(u) = Manager AND u.userId <> :userId")
    Stream<User> findManagersByUserIdNot(UUID userId);

    @Query("SELECT u FROM User u WHERE TYPE(u) = Consultant AND u.userId <> :userId")
    Stream<User> findConsultantsByUserIdNot(UUID userId);
}
