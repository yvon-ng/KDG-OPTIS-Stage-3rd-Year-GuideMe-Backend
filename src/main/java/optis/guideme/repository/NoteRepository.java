package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<@NonNull Note,@NonNull UUID> {
}
