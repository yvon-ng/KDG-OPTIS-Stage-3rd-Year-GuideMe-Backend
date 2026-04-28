package optis.guideme.api.mapper.request;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewNoteMapper {
    public Note toDomain(NewNoteDto newNoteDto) {
        Visibility visibility = newNoteDto.visibility() == null
                ? Visibility.PRIVATE
                : Visibility.valueOf(newNoteDto.visibility().toUpperCase());

        return new Note(
                newNoteDto.title(),
                newNoteDto.description(),
                visibility,
                newNoteDto.meeting(),
                newNoteDto.sharedUsers()
        );
    }

    public NoteDto toResponseDto(Note note) {
        return new NoteDto(
                note.getNoteId(),
                note.getTitle(),
                note.getDescription(),
                note.getVisibility().toString(),
                note.getOwner()
        );
    }
}
