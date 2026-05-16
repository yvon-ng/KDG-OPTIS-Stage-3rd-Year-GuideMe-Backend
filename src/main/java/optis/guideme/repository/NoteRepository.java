package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface NoteRepository extends JpaRepository<@NonNull Note,@NonNull UUID> {
    Stream<Note> findAllByOwnerAndVisibility(UUID owner, Visibility visibility);

    Stream<Note> findAllByOwnerAndMeetingIdIsNotNull(UUID owner);

    Stream<Note> findAllByOwner(UUID userId);

//    Note findNoteByNoteId(UUID noteId);
}
