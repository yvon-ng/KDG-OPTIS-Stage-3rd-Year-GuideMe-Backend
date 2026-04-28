package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewNoteDto;
import optis.guideme.api.dto.response.NoteDto;
import optis.guideme.application.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
