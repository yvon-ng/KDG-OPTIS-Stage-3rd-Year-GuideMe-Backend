package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<@NonNull Note,@NonNull UUID> {
    List<Note> findAllByOwner(UUID owner);

    List<Note> getAllByOwnerAndVisibility(UUID owner, Visibility visibility);

    List<Note> getAllByOwnerAndMeetingIdIsNotNull(UUID owner);

    Note findNoteByNoteId(UUID noteId);
}
