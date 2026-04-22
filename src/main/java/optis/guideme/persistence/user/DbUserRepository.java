package optis.guideme.persistence.user;

import optis.guideme.domain.user.User;
import optis.guideme.domain.user.UserId;
import optis.guideme.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DbUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public DbUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> getUser(UserId userId) {
        return this.jpaUserRepository.findById(userId.id()).map(JpaUserEntity::toDomain);
    }
}
