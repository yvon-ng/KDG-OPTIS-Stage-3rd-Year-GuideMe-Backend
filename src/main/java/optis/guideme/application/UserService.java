package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewClientScoreDto;
import optis.guideme.api.dto.response.ClientScoreDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.ClientScoreMapper;
import optis.guideme.api.mapper.UserMapper;
import optis.guideme.domain.exception.ForbiddenException;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.user.*;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
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

    public List<User> getUsersEntitiesByIds(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    public List<UserDto> getUsersByIds(List<UUID> ids) {
        return userRepository.findAllById(ids).stream().map(userMapper::toResponseDto).toList();
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


    public List<ClientScoreDto> getLatestClientScores(UUID userId, int limit) {
        Consultant consultant = getUserEntity(userId).asConsultant();
        return consultant.getLatestClientScores(limit).stream().map(clientScoreMapper::toResponseDto).toList();
    }

    public List<ClientScoreDto> rateCurrentClient(UUID userId, NewClientScoreDto newClientScoreDto, int limit) {
        Consultant consultant = getUserEntity(userId).asConsultant();
        ClientScore newClientScore = new ClientScore(consultant.getClientName(), Score.fromValue(newClientScoreDto.score()), LocalDateTime.now());
        consultant.addClientScore(newClientScore);
        // db doesnt update on runtime after adding a new client score so needed to do in memory
        return consultant.getClientScores().stream()
                .sorted(Comparator.comparing(ClientScore::getDateTime).reversed())
                .limit(limit)
                .map(clientScoreMapper::toResponseDto)
                .toList();
    }
}
