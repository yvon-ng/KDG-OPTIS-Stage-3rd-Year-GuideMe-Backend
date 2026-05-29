package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateUserDto;
import optis.guideme.api.dto.request.NewClientDto;
import optis.guideme.api.dto.request.NewClientScoreDto;
import optis.guideme.api.dto.response.ClientScoreDto;
import optis.guideme.api.dto.response.UserDto;
import optis.guideme.api.mapper.ClientScoreMapper;
import optis.guideme.api.mapper.UserMapper;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.user.*;
import optis.guideme.repository.UserRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private final NotificationService notificationService;

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
        return userRepository.findAll().stream().map(userMapper::toResponseDto).toList();
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

    public UserDto updateUserDetails(UUID userId, UpdateUserDto updateUserDto) {
        User user = getUser(userId);
        if (!updateUserDto.firstName().isEmpty()) user.changeFirstName(updateUserDto.firstName());
        if (!updateUserDto.lastName().isEmpty()) user.changeLastName(updateUserDto.lastName());
        if (!updateUserDto.avatar().isEmpty()) user.changeAvatar(updateUserDto.avatar());
        return userMapper.toResponseDto(userRepository.save(user));
    }

    public void addNewClient(UUID userId, NewClientDto newClientDto) {
        Consultant consultant = getUser(userId).asConsultant();
        consultant.asConsultant().changeClient(newClientDto.clientName(), newClientDto.startDateAtClient().atZone(ZoneId.systemDefault()).toLocalDateTime());
        ClientScore newClientScore = new ClientScore(newClientDto.clientName(), Score.TEN, LocalDateTime.now());
        consultant.addClientScore(newClientScore);
    }

    public List<UserDto> getAllManagersExceptMe(UUID userId) {
        return userRepository.findManagersByUserIdNot(userId).map(userMapper::toResponseDto).toList();
    }

    public List<UserDto> getAllConsultantsExceptMe(UUID userId) {
        return userRepository.findConsultantsByUserIdNot(userId).map(userMapper::toResponseDto).toList();
    }

    public void getOrCreateUser(UUID oid, String email, Role role) {
//        User user = userRepository.findById(oid).orElse(null);
//        if (user == null) {
//            User newUser = (role == Role.MANAGER)
//                    ? new Manager(oid, email)
//                    : new Consultant(oid, email);
//            userRepository.save(newUser);
//        }
        // !!!now this doesnt use constructo!!!
        userRepository.insertIfNotExists(oid, email, role.name(), User.DEFAULT_PROFILE_PICTURE_S3_KEY);
        User user = getUser(oid);
        //send notification
        if (user.markProfileNotificationSent()) {
            userRepository.save(user);
            notificationService.sendFillInProfileNotification(oid);
        }
    }
}
