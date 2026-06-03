package optis.guideme.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateUserDto;
import optis.guideme.api.dto.request.NewClientDto;
import optis.guideme.api.dto.request.NewClientScoreDto;
import optis.guideme.api.dto.response.ClientScoreDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.configs.CurrentUserProvider;
import optis.guideme.application.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("me")
    public UserDto me() {
        return userService.get(CurrentUserProvider.getUserId());
    }

    @PutMapping("{userId}")
    public UserDto updateUserDetails(@PathVariable UUID userId, @RequestBody @Valid UpdateUserDto updateUserDto) {
        return userService.updateUserDetails(userId, updateUserDto);
    }

    @PostMapping("{userId}")
    public void addNewClient(@PathVariable UUID userId, @RequestBody @Valid NewClientDto newClientDto) {
        userService.addNewClient(userId, newClientDto);
    }

    @GetMapping("all")
    public List<UserDto> all() {
        return userService.getAllUsers();
    }

    @GetMapping("others")
    public List<UserDto> getAllUsersExceptMe() {
        return userService.getAllUsersExceptMe(CurrentUserProvider.getUserId());
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
        return userService.getLatestClientScores(CurrentUserProvider.getUserId(), limit);
    }

    @GetMapping("client-scores/recent/{userId}")
    public List<ClientScoreDto> getUserLatestClientScores(@PathVariable UUID userId, @RequestParam(defaultValue = "2") int limit) {
        return userService.getLatestClientScores(userId, limit);
    }

    @PostMapping("client-scores/new")
    public void rateCurrentClient(@RequestBody NewClientScoreDto newClientScoreDto) {
        userService.rateCurrentClient(CurrentUserProvider.getUserId(), newClientScoreDto);
    }

    @GetMapping("client-scores/team")
    public Map<UUID, ClientScoreDto> getLatestScorePerConsultant() {
        return userService.getLatestScorePerConsultant();
    }

    @GetMapping("notes/shared-users")
    public List<UserDto> getSharedUsers(){
        return userService.getSharedUsers(CurrentUserProvider.getUserId());
    }
}
