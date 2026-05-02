package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.application.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserDto me(@AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return userService.getUser(userId);
    }

//    @GetMapping("/invite")
//    public ResponseEntity<@NonNull InvitationDto> inviteUser(@AuthenticationPrincipal Jwt jwt, NewInvitationDto newInvitationDto){
//        UserId managerId = new UserId(UUID.fromString(jwt.getSubject()));
//        User user = userService.addUser(managerId, newInvitationDto.email(), newInvitationDto.role())
//                .orElseThrow(() -> new NotFoundException("User not found with id: " + managerId)); //todo
//        return ResponseEntity.ok();
//    }

    @GetMapping("/others")
    public List<UserDto> getAllUsersExceptMe(@AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getClaim("oid"));
        return userService.getAllUsersExceptMe(userId);
    }
}
