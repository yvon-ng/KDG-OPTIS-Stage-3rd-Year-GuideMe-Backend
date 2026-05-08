package optis.guideme.domain.meeting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;

import java.util.UUID;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
public class Attendee {

    @Id
    @GeneratedValue
    private UUID attendeeId;
    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private Role role;

    //domain constructor
    public Attendee(Role role, User user) {
        this.role = role;
        this.user = user;
    }

    public void addAttendeeToMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
