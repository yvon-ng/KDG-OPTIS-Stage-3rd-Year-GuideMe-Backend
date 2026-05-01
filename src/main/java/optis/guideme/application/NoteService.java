package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.mapper.request.NewNoteMapper;
import optis.guideme.domain.note.Note;
import optis.guideme.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NewNoteMapper newNoteMapper;

    public NoteDto createNewNote(UUID userId, NewNoteDto newNoteDto) {
        Note note = newNoteMapper.toDomain(newNoteDto);
        note.assignOwner(userId);
        return newNoteMapper.toResponseDto(noteRepository.save(note));
    }

    public List<NoteDto> getAllNotes(UUID userId) {
        return noteRepository.findAllByOwner(userId).stream().map(newNoteMapper::toResponseDto).toList();
    }
}
