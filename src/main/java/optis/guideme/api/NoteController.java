package optis.guideme.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateNoteDto;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.dto.response.NotePreviewDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notes")
public class NoteController {
    private final NoteService noteService;
    private final CurrentUserProvider currentUserProvider;

    @PostMapping
    public NoteDto createNewNote(@RequestBody @Valid NewNoteDto newNoteDto){
        return noteService.createNewNote(currentUserProvider.getUserId(), newNoteDto);
    }

    @GetMapping
    public List<NotePreviewDto> getMyNotes(){
        return noteService.getAllNotes(currentUserProvider.getUserId());
    }

    @GetMapping("private")
    public List<NotePreviewDto> getMyPrivateNotes(){
        return noteService.getAllPrivateNotes(currentUserProvider.getUserId());
    }

    @GetMapping("meetings")
    public List<NotePreviewDto> getMyMeetingNotes(){
        return noteService.getAllMeetingNotes(currentUserProvider.getUserId());
    }

    @GetMapping("{noteId}")
    public NoteDto getNote(@PathVariable UUID noteId){
        return noteService.getNote(noteId);
    }

    @PutMapping("{noteId}")
    public NoteDto updateNote(@PathVariable UUID noteId, @RequestBody UpdateNoteDto updateNoteDto){
        return noteService.updateNote(currentUserProvider.getUserId(), noteId, updateNoteDto);
    }
}
