package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface NoteRepository extends JpaRepository<@NonNull Note, @NonNull UUID> {
    Stream<Note> findAllByOwnerOrderByCreationTimeDesc(UUID userId);

    Stream<Note> findAllByOwnerAndVisibilityOrderByCreationTimeDesc(UUID userId, Visibility visibility);

    Stream<Note> findAllByOwnerAndMeetingIdIsNotNullOrderByCreationTimeDesc(UUID userId);

    // in repository
    Stream<Note> findAllByOwnerAndSharedUsersContaining(UUID owner, UUID sharedUser);
}
