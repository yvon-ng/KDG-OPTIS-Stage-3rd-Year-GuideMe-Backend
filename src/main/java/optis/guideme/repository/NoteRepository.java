package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
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

    // in repository
    Stream<Note> findAllByOwnerAndSharedUsersContaining(UUID owner, UUID sharedUser);
}
