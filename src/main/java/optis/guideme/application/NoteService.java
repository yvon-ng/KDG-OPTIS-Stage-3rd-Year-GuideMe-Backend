package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateNoteDto;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.dto.response.NotesDto;
import optis.guideme.api.mapper.request.NewNoteMapper;
import optis.guideme.domain.exception.ForbiddenException;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import optis.guideme.repository.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
        return newNoteMapper.toNoteResponseDto(noteRepository.save(note));
    }

    public List<NotesDto> getAllNotes(UUID userId) {
        return noteRepository.findAllByOwner(userId).stream().map(newNoteMapper::toNotesResponseDto).toList();
    }

    public List<NotesDto> getAllPrivateNotes(UUID userId) {
        return noteRepository.getAllByOwnerAndVisibility(userId, Visibility.PRIVATE).stream().map(newNoteMapper::toNotesResponseDto).toList();
    }

    public List<NotesDto> getAllMeetingNotes(UUID userId) {
        return noteRepository.getAllByOwnerAndMeetingIdIsNotNull(userId).stream().map(newNoteMapper::toNotesResponseDto).toList();
    }

    public NoteDto getNote(UUID noteId) {
        return newNoteMapper.toNoteResponseDto(noteRepository.findNoteByNoteId(noteId));
    }

    public NoteDto updateNote(UUID userId, UUID noteId, UpdateNoteDto updateNoteDto) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException("Note not found"));
        note.update(userId, updateNoteDto);
        return newNoteMapper.toNoteResponseDto(noteRepository.save(note));
    }
}
