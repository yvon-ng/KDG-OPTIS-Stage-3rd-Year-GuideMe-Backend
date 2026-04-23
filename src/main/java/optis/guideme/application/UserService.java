package optis.guideme.application;

import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.factories.UserFactory;
import optis.guideme.domain.user.*;
import optis.guideme.persistence.user.JpaUserEntity;
import optis.guideme.persistence.user.JpaUserRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User findById(UserId userId) {
        return userRepository.findById(userId.userId());
    }

    private JpaUserEntity toJpa(User user){
        JpaUserEntity entity = new JpaUserEntity();
        entity.setUserId(user.getId().userId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());

        if (user instanceof Manager) {
            entity.setRole(Role.MANAGER);
        } else {
            entity.setRole(Role.CONSULTANT);
        }

        return entity;
    }

    //method to easily check if a user is a manaeger or no
    public boolean isManager(UserId userId) {
        return userRepository.existsByUserIdAndRole(userId.userId(), Role.MANAGER);
    }


}
