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
    public void updateMeetingRequest(){} // for rescheduling a meetingId from a consultant
    public void updateConsultant(){} // for updating consultant details

    public Manager(UUID userId , String firstName, String lastName, String email, String avatar) {
        super(userId, firstName, lastName, email, avatar);
    }

    @Override
    public Role getRole() {
        return Role.MANAGER;
    }
}
