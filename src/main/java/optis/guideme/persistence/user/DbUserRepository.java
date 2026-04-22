package optis.guideme.persistence.user;

import optis.guideme.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DbUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public DbUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }
}
