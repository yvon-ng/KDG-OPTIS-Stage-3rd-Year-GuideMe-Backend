package optis.guideme.application;

import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.factories.UserFactory;
import optis.guideme.domain.user.*;
import optis.guideme.persistence.user.JpaUserEntity;
import optis.guideme.persistence.user.JpaUserRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final JpaUserRepository jpaUserRepository;
    private final UserFactory userFactory;

    public UserService(JpaUserRepository jpaUserRepository, UserFactory userFactory) {
        this.jpaUserRepository = jpaUserRepository;
        this.userFactory = userFactory;
    }

    public User findById(UserId userId) {
        JpaUserEntity user = jpaUserRepository.findById(userId.userId())
                .orElseThrow(() -> new NotFoundException("user not found with id: " + userId));
        return toDomain(user);
    }

    private User toDomain(JpaUserEntity user) {
        UserId id = UserId.of(user.getUserId());

        return switch(user.getRole()) {
            case MANAGER -> new Manager(
                    id,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail()
            );
            case CONSULTANT ->  new Consultant(
                    id,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail()
            );
        };
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
        return jpaUserRepository.existsByUserIdAndRole(userId.userId(), Role.MANAGER);
    }


}
