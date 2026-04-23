package optis.guideme.domain.meeting;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.user.User;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="attendees")
public class Attendee {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Meeting meeting;

    public Attendee(Meeting meeting, User user) {
        this.meeting = meeting;
        this.user = user;
    }
}
