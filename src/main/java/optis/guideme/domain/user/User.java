package optis.guideme.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role")
public abstract class User {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;

    //    private List<Goal> goals;
//    private List<Reminder> reminders;
}
