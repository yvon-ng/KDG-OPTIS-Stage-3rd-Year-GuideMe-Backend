package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateNoteDto;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.dto.response.NotePreviewDto;
import optis.guideme.api.mapper.NoteMapper;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
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
    private final NoteMapper noteMapper;

    public NoteDto createNewNote(UUID userId, NewNoteDto newNoteDto) {
        Note note = noteMapper.toDomain(newNoteDto);
        note.assignOwner(userId);
        return noteMapper.toNoteResponseDto(noteRepository.save(note));
    }

    public List<NotePreviewDto> getAllNotes(UUID userId) {
        return noteRepository.getAllByOwner(userId).map(noteMapper::toNotesResponseDto).toList();
    }

    public List<NotePreviewDto> getAllPrivateNotes(UUID userId) {
        return noteRepository.getAllByOwnerAndVisibility(userId, Visibility.PRIVATE).map(noteMapper::toNotesResponseDto).toList();
    }

    public List<NotePreviewDto> getAllMeetingNotes(UUID userId) {
        return noteRepository.getAllByOwnerAndMeetingIdIsNotNull(userId).map(noteMapper::toNotesResponseDto).toList();
    }

    public NoteDto get(UUID noteId) {
        return noteMapper.toNoteResponseDto(getNote(noteId));
    }

    public NoteDto updateNote(UUID userId, UUID noteId, UpdateNoteDto updateNoteDto) {
        Note note = getNote(noteId);
        note.update(userId, updateNoteDto);
        return noteMapper.toNoteResponseDto(noteRepository.save(note));
    }

    private Note getNote(UUID noteId){
        return noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException("Note Not Found with Id: " + noteId));
    }
}
