package optis.guideme.persistence.user;

import jakarta.persistence.*;
import lombok.Getter;
import optis.guideme.domain.user.*;

import java.util.UUID;

@Entity
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

    public User toDomain() {
        return switch(role) {
            case MANAGER -> new Manager(
                    new UserId(userId),
                    firstName,
                    lastName,
                    email
            );
            case CONSULTANT ->  new Consultant(
                    new UserId(userId),
                    firstName,
                    lastName,
                    email
                    //TODO consultants attributes
            );
        };
    }
}

