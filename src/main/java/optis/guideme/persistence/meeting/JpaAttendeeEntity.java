package optis.guideme.persistence.meeting;

import jakarta.persistence.*;
import optis.guideme.persistence.user.JpaUserEntity;

import java.util.UUID;

@Entity
@Table(name="attendees")
public class JpaAttendeeEntity {
    @Id
    private UUID id;
    @ManyToOne
    private JpaUserEntity user;
    @ManyToOne
    private JpaMeetingEntity meeting;
}
