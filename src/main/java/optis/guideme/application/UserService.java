package optis.guideme.application;

import optis.guideme.domain.user.Invitation;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;
import optis.guideme.domain.user.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    //TODO repo

    public Optional<User> getUser(UserId userId) {
        return Optional.empty();
    }

    public Optional<User> addUser(UserId userId, String email, Role role) {
        Invitation invitation = new Invitation(email, role);

        return Optional.empty();
    }
}
