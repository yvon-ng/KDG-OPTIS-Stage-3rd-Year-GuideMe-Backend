package optis.guideme.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateNoteDto;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.dto.response.NotePreviewDto;
import optis.guideme.configs.CurrentUserProvider;
import optis.guideme.application.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public NoteDto createNewNote(@RequestBody @Valid NewNoteDto newNoteDto){
        return noteService.createNewNote(CurrentUserProvider.getUserId(), newNoteDto);
    }

    @GetMapping
    public List<NotePreviewDto> getMyNotes(){
        return noteService.getAllNotes(CurrentUserProvider.getUserId());
    }

    @GetMapping("private")
    public List<NotePreviewDto> getMyPrivateNotes(){
        return noteService.getAllPrivateNotes(CurrentUserProvider.getUserId());
    }

    @GetMapping("meetings")
    public List<NotePreviewDto> getMyMeetingNotes(){
        return noteService.getAllMeetingNotes(CurrentUserProvider.getUserId());
    }

    @GetMapping("{noteId}")
    public NoteDto getNote(@PathVariable UUID noteId){
        return noteService.get(noteId);
    }

    @PutMapping("{noteId}")
    public NoteDto updateNote(@PathVariable UUID noteId, @RequestBody @Valid UpdateNoteDto updateNoteDto){
        return noteService.updateNote(CurrentUserProvider.getUserId(), noteId, updateNoteDto);
    }

    @GetMapping("sharedWith/{ownerId}")
    public List<NoteDto> getNotesSharedWithCurrentUser(@PathVariable UUID ownerId) {
        UUID currentUserId = CurrentUserProvider.getUserId();
        return noteService.getNotesSharedWithUser(ownerId, currentUserId);
    }
}
