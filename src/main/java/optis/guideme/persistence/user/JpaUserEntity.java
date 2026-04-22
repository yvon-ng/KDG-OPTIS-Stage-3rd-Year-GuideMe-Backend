package optis.guideme.persistence.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Table(name = "users")
public class JpaUserEntity {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
}
