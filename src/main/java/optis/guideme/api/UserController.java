package optis.guideme.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateUserDto;
import optis.guideme.api.dto.request.NewClientDto;
import optis.guideme.api.dto.request.NewClientScoreDto;
import optis.guideme.api.dto.response.ClientScoreDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping("me")
    public UserDto me() {
        return userService.get(currentUserProvider.getUserId());
    }

    @PutMapping("me")
    public UserDto updateUserDetails(@RequestBody @Valid UpdateUserDto updateUserDto) {
        return userService.updateUserDetails(currentUserProvider.getUserId(), updateUserDto);
    }

    @PostMapping("me")
    public void addNewClient(@RequestBody @Valid NewClientDto newClientDto) {
        userService.addNewClient(currentUserProvider.getUserId(), newClientDto);
    }

    @GetMapping("all")
    public List<UserDto> all() {
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
    public List<UserDto> getAllUsersExceptMe() {
        return userService.getAllUsersExceptMe(currentUserProvider.getUserId());
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable UUID id) {
        return userService.get(id);
    }

    @PostMapping("attendees")
    public List<UserDto> getUsersByIds(@RequestBody List<UUID> ids) {
        return userService.getByIds(ids);
    }

    @GetMapping("client-scores/recent")
    public List<ClientScoreDto> getLatestClientScores(@RequestParam(defaultValue = "2") int limit) {
        return userService.getLatestClientScores(currentUserProvider.getUserId(), limit);
    }

    @GetMapping("client-scores/recent/{userId}")
    public List<ClientScoreDto> getUserLatestClientScores(@PathVariable UUID userId, @RequestParam(defaultValue = "2") int limit) {
        return userService.getLatestClientScores(userId, limit);
    }

    @PostMapping("client-scores/new")
    public void rateCurrentClient(@RequestBody NewClientScoreDto newClientScoreDto) {
        userService.rateCurrentClient(currentUserProvider.getUserId(), newClientScoreDto);
    }
}
