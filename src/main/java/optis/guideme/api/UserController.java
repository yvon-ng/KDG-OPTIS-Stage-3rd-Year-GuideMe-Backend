package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping("me")
    public UserDto me(){
        return userService.getUser(currentUserProvider.getUserId());
    }

    @GetMapping("all")
    public List<UserDto> all(){
        return userService.getAllUsers();
    }


//    @GetMapping("/invite")
//    public ResponseEntity<@NonNull InvitationDto> inviteUser(@AuthenticationPrincipal Jwt jwt, NewInvitationDto newInvitationDto){
//        UserId managerId = new UserId(UUID.fromString(jwt.getSubject()));
//        User user = userService.addUser(managerId, newInvitationDto.email(), newInvitationDto.role())
//                .orElseThrow(() -> new NotFoundException("User not found with id: " + managerId)); //todo
//        return ResponseEntity.ok();
//    }

    @GetMapping("others")
    public List<UserDto> getAllUsersExceptMe(){
        return userService.getAllUsersExceptMe(currentUserProvider.getUserId());
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable UUID id){
       return userService.getUser(id);
    }

}
