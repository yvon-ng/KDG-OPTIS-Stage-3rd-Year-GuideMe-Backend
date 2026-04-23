package optis.guideme.api;

import lombok.NonNull;
import optis.guideme.api.dto.UserDto;
import optis.guideme.application.UserService;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<@NonNull UserDto> me(@AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getSubject());
        User user = userService.getUser(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        return ResponseEntity.ok(UserDto.fromDomain(user));
    }


    @GetMapping("/test")
    public void testCall(@AuthenticationPrincipal Jwt jwt){
        logger.info("testCall is gelukt");
    }

//    @GetMapping("/invite")
//    public ResponseEntity<@NonNull InvitationDto> inviteUser(@AuthenticationPrincipal Jwt jwt, NewInvitationDto newInvitationDto){
//        UserId managerId = new UserId(UUID.fromString(jwt.getSubject()));
//        User user = userService.addUser(managerId, newInvitationDto.email(), newInvitationDto.role())
//                .orElseThrow(() -> new NotFoundException("User not found with id: " + managerId)); //todo
//        return ResponseEntity.ok();
//    }
}
