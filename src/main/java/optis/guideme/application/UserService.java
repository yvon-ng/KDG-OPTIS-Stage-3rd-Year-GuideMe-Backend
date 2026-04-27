package optis.guideme.application;

import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.factories.UserFactory;
import optis.guideme.domain.user.*;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(()  -> new NotFoundException("User not found with id: " + userId));
    }
}
