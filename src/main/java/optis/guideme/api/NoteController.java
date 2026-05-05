package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateNoteDto;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.api.dto.response.NotesDto;
import optis.guideme.application.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public NoteDto createNewNote(@AuthenticationPrincipal Jwt jwt, @RequestBody NewNoteDto newNoteDto){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return noteService.createNewNote(userId, newNoteDto);
    }

    @GetMapping
    public List<NotesDto> getMyNotes(@AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return noteService.getAllNotes(userId);
    }

    @GetMapping("/private")
    public List<NotesDto> getMyPrivateNotes(@AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return noteService.getAllPrivateNotes(userId);
    }

    @GetMapping("/meetings")
    public List<NotesDto> getMyMeetingNotes(@AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return noteService.getAllMeetingNotes(userId);
    }

    @GetMapping("/{noteId}")
    public NoteDto getNote(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID noteId){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return noteService.getNote(noteId);
    }

    @PutMapping("/{noteId}")
    public NoteDto updateNote(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID noteId, @RequestBody UpdateNoteDto updateNoteDto){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return noteService.updateNote(userId, noteId, updateNoteDto);
    }
}
