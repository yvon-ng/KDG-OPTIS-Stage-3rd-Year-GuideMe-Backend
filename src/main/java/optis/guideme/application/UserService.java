package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.UserMapper;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.user.*;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(UUID userId) {
        return UserMapper.toDto(this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId)));
    }

    public Optional<User> addUser(UUID userId, String email, Role role) {
        Invitation invitation = new Invitation(email, role);

        return Optional.empty();
    }
}
