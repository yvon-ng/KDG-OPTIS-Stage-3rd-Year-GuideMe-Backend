package optis.guideme.application;

import optis.guideme.domain.user.User;
import optis.guideme.domain.user.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    public Optional<User> getUser(UserId userId) {
        return Optional.empty();
    }
}
