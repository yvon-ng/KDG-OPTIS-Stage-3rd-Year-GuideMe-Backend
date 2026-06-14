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
import optis.guideme.repository.NoteRepository;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ClientScoreMapper clientScoreMapper;
    private final NotificationService notificationService;
    private final NoteRepository noteRepository;

    public UserDto get(UUID userId) {
        return userMapper.toResponseDto(getUser(userId));
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
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found with Id: " + userId));
    }

    public List<ClientScoreDto> getLatestClientScores(UUID userId, int limit) {
        Consultant consultant = getUser(userId).asConsultant();
        String currentClient = consultant.getClientName();
        return consultant.getLatestClientScores(limit).stream()
                .filter(s -> s.getClientName().equals(currentClient))
                .map(clientScoreMapper::toResponseDto)
                .toList();
    }

    public void rateCurrentClient(UUID userId, NewClientScoreDto newClientScoreDto) {
        Consultant consultant = getUser(userId).asConsultant();
        ClientScore newClientScore = new ClientScore(consultant.getClientName(), clientScoreMapper.newClientScoreToDomain(newClientScoreDto), LocalDateTime.now());
        consultant.addClientScore(newClientScore);

        if (newClientScore.getScore().isLow()) {
            List<User> managers = getAllManagers();
            notificationService.sendLowClientScoreNotification(managers, consultant.getFirstName(), consultant.getLastName(), newClientScore.getScore().getValue());
        }

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
        // !!!now this doesnt use constructor!!!
        userRepository.insertIfNotExists(oid, email, role.name(), User.DEFAULT_PROFILE_PICTURE_S3_KEY);
        User user = getUser(oid);
        //send notification
        if (user.markProfileNotificationSent()) {
            userRepository.save(user);
            notificationService.sendFillInProfileNotification(oid);
        }
    }

    public List<User> getAllConsultants() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.CONSULTANT)
                .toList();
    }

    public Map<UUID, ClientScoreDto> getLatestScorePerConsultant() {
        return getAllConsultants().stream()
                .filter(c -> !getLatestClientScores(c.getUserId(), 1).isEmpty())
                .collect(Collectors.toMap(
                        User::getUserId,
                        c -> getLatestClientScores(c.getUserId(), 1).getFirst()
                ));
    }

    public List<UserDto> getSharedUsers(UUID userId) {
        return noteRepository.findDistinctSharedUsersByOwner(userId)
                .map(userMapper::toResponseDto)
                .toList();
    }

    private List<User> getAllManagers() {
        return userRepository.getAllManagers().toList();
    }
}
