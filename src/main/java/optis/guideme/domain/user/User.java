package optis.guideme.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.ForbiddenException;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public abstract class User {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;

//    private List<Goal> goals;
//    private List<Reminder> reminders;

    public Role getRole(){
        return Role.valueOf(this.getClass().getAnnotation(DiscriminatorValue.class).value());
    }

    public Consultant asConsultant() {
        throw new ForbiddenException("User is not a consultant, Must be a consultant to give a client a score");
    }

    public void changeFirstName(String firstName){
        this.firstName = firstName;
    }

    public void changeLastName(String lastName){
        this.lastName = lastName;
    }
    public void changeAvatar(String avatar){
        this.avatar = avatar;
    }
}
