package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteDto addNote(UUID userId, NewNoteDto newNoteDto) {
        return mapper.toResponseDto()
    }
}
