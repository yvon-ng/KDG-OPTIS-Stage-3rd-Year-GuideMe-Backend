package optis.guideme.application;


import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService {
    private final UserService userService;

    public List<UserDto> getAllManagersExceptMe(UUID userId){
        return userService.getAllManagersExceptMe(userId);
    }

    public List<UserDto> getAllConsultantsExceptMe(UUID userId){
        return userService.getAllConsultantsExceptMe(userId);
    }
}
