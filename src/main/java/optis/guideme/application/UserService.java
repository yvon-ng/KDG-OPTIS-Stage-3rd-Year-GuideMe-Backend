package optis.guideme.application;

import optis.guideme.domain.user.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(UserId userId) {
        return Optional.empty();
    }

    public Optional<User> addUser(UserId userId, String email, Role role) {
        Invitation invitation = new Invitation(email, role);

        return Optional.empty();
    }
}
