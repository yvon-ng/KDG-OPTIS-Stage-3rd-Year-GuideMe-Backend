package optis.guideme.api.mapper.request;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.dto.response.NotesDto;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewNoteMapper {
    public Note toDomain(NewNoteDto newNoteDto) {
        Visibility visibility = newNoteDto.visibility() == null
                ? Visibility.PRIVATE
                : newNoteDto.visibility();

        return new Note(
                newNoteDto.title(),
                newNoteDto.description(),
                visibility,
                newNoteDto.meetingId(),
                newNoteDto.sharedUsers()
        );
    }

    public NoteDto toNoteResponseDto(Note note) {
        return new NoteDto(
                note.getNoteId(),
                note.getTitle(),
                note.getDescription(),
                note.getVisibility(),
                note.getOwner(),
                note.getCreationTime(),
                note.getMeetingId(),
                note.getSharedUsers()
        );
    }

    public NotesDto toNotesResponseDto(Note note) {
        return new NotesDto(
                note.getNoteId(),
                note.getTitle(),
                note.getVisibility(),
                note.getCreationTime()
        );
    }
}
