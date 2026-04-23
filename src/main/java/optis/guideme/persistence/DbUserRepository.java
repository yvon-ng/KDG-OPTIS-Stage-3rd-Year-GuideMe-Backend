package optis.guideme.persistence;

import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;
import optis.guideme.domain.user.UserRepository;
import optis.guideme.persistence.user.JpaUserEntity;
import optis.guideme.persistence.user.JpaUserRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public class DbUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public DbUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public boolean existsByUserIdAndRole(UUID uuid, Role role) {
        return jpaUserRepository.existsByUserIdAndRole(uuid, role);
    }

    @Override
    public User findById(UUID uuid) {
        JpaUserEntity entity = jpaUserRepository.findById(uuid).orElseThrow();
        return entity.toDomain(entity);
    }
}
