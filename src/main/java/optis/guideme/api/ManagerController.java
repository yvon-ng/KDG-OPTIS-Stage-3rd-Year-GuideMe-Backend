package optis.guideme.api;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.ManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/managers")
public class ManagerController {
    private final ManagerService managerService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping
    public List<UserDto> getAllManagers(){
        return managerService.getAllManagersExceptMe(currentUserProvider.getUserId());
    }

    @GetMapping("/consultants")
    public List<UserDto> getAllConsultants(){
        return managerService.getAllConsultantsExceptMe(currentUserProvider.getUserId());
    }
}
