package optis.guideme.persistence.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import optis.guideme.domain.user.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "users")
public class JpaUserEntity {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User toDomain(JpaUserEntity user) {
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
}
