package optis.guideme.api.mapper.request;

import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.dto.response.NotePreviewDto;
import optis.guideme.domain.note.Note;
import optis.guideme.domain.note.Visibility;
import org.springframework.stereotype.Component;

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

    public NotePreviewDto toNotesResponseDto(Note note) {
        return new NotePreviewDto(
                note.getNoteId(),
                note.getTitle(),
                note.getVisibility(),
                note.getCreationTime()
        );
    }
}
