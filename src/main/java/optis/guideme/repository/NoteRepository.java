package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import optis.guideme.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface NoteRepository extends JpaRepository<@NonNull Note, @NonNull UUID> {
    Stream<Note> findAllByOwnerAndVisibilityOrderByCreationTimeDesc(UUID userId, Visibility visibility);

    Stream<Note> findAllByOwnerAndMeetingIdIsNotNullOrderByCreationTimeDesc(UUID userId);

    @Query("""
            SELECT DISTINCT n
            FROM Note n
            LEFT JOIN n.sharedUsers su
            WHERE n.owner = :userId
               OR su = :userId
            ORDER BY n.creationTime DESC
            """)
    Stream<Note> findAllAccessibleNotes(@Param("userId") UUID userId);

    @Query("""
        SELECT DISTINCT n
        FROM Note n
        LEFT JOIN n.sharedUsers su
        WHERE (n.owner = :viewedUserId AND su = :currentUserId)
           OR (n.owner = :currentUserId AND su = :viewedUserId)
        ORDER BY n.creationTime DESC
        """)
    Stream<Note> findAllSharedBetweenUsers(@Param("currentUserId") UUID currentUserId,
                                           @Param("viewedUserId") UUID viewedUserId);

    @Query("SELECT DISTINCT u FROM Note n JOIN n.sharedUsers su JOIN User u ON u.userId = su WHERE n.owner = :userId AND su != :userId")
    Stream<User> findDistinctSharedUsersByOwner(UUID userId);
}
