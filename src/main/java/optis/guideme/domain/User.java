package optis.guideme.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public abstract class User {
    private String firstName;
    private String lastName;
    private String email;
}
