package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.UserMapper;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.user.*;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUser(UUID userId) {
        return userMapper.toResponseDto(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId)));
    }

    public Optional<User> addUser(UUID userId, String email, Role role) {
        Invitation invitation = new Invitation(email, role);
        return Optional.empty();
    }

    public List<UserDto> getAllUsersExceptMe(UUID userId) {
        return userRepository.findAllByUserIdNot(userId).stream().map(userMapper::toResponseDto).toList();
    }

    public List<User> getUsersByIds(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    public User getUserEntity(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }
}
