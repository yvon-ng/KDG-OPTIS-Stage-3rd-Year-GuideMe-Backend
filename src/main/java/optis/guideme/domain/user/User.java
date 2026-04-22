package optis.guideme.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class User {
    private UserId id;
    private String firstName;
    private String lastName;
    private String email;

//    private List<Goal> goals;
//    private List<Reminder> reminders;
}
