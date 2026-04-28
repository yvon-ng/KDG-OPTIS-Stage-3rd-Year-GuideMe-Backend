package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.factories.UserFactory;
import optis.guideme.domain.user.*;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(()  -> new NotFoundException("User not found with id: " + userId));
    }
}
