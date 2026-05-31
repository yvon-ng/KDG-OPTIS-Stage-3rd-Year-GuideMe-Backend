package optis.guideme.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {
    public void updateMeeting(){} // for adjusting a meetingId
    public Manager(UUID userId , String firstName, String lastName, String email, String avatar) {
        super(userId, firstName, lastName, email, avatar, false);
    }

    public Manager(UUID userId, String email) {
        super(userId, email);
    }
}
