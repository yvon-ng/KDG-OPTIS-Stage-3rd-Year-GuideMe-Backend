package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewClientScoreDto;
import optis.guideme.api.dto.response.ClientScoreDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.ClientScoreMapper;
import optis.guideme.api.mapper.UserMapper;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.user.*;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ClientScoreMapper clientScoreMapper;

    public UserDto get(UUID userId) {
        return userMapper.toResponseDto(getUser(userId));
    }

    public Optional<User> addUser(UUID userId, String email, Role role) {
        Invitation invitation = new Invitation(userId, email, role);
        return Optional.empty();
    }

    public List<UserDto> getAllUsersExceptMe(UUID userId) {
        return userRepository.findAllByUserIdNot(userId).map(userMapper::toResponseDto).toList();
    }

    public List<User> getUsersByIds(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    public List<UserDto> getByIds(List<UUID> ids) {
        return userRepository.findAllById(ids).stream().map(userMapper::toResponseDto).toList();
    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAll().map(userMapper::toResponseDto).toList();
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public List<ClientScoreDto> getLatestClientScores(UUID userId, int limit) {
        return getUser(userId).asConsultant().getLatestClientScores(limit).stream().map(clientScoreMapper::toResponseDto).toList();
    }

    public void rateCurrentClient(UUID userId, NewClientScoreDto newClientScoreDto) {
        Consultant consultant = getUser(userId).asConsultant();
        ClientScore newClientScore = new ClientScore(consultant.getClientName(), clientScoreMapper.newClientScoreToDomain(newClientScoreDto), LocalDateTime.now());
        consultant.addClientScore(newClientScore);
    }
}
