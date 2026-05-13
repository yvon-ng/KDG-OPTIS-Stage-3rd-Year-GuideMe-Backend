package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface NoteRepository extends JpaRepository<@NonNull Note,@NonNull UUID> {
    Stream<Note> getAllByOwnerAndVisibility(UUID owner, Visibility visibility);

    Stream<Note> getAllByOwnerAndMeetingIdIsNotNull(UUID owner);

    Stream<Note> getAllByOwner(UUID userId);

//    Note findNoteByNoteId(UUID noteId);
}
