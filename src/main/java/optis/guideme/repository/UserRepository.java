package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query(value = "INSERT INTO users (user_id, email, role, avatar, profile_notification_sent) VALUES (:userId, :email, :role, :defaultProfilePictureS3Key, FALSE) ON CONFLICT (user_id) DO NOTHING", nativeQuery = true)
    void insertIfNotExists(@Param("userId") UUID userId, @Param("email") String email, @Param("role") String role, @Param("defaultProfilePictureS3Key") String defaultProfilePictureS3Key);

    @Query("SELECT u FROM User u WHERE TYPE(u) = Manager")
    Stream<User> getAllManagers();
}
