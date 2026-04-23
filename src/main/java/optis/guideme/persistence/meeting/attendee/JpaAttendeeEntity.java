package optis.guideme.persistence.meeting.attendee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import optis.guideme.domain.user.UserId;
import optis.guideme.persistence.meeting.JpaMeetingEntity;
import optis.guideme.persistence.user.JpaUserEntity;

import java.util.UUID;

@IdClass(AttendeeId.class)
@Entity
@Setter
@Getter
@Table(name="attendees")
public class JpaAttendeeEntity {

    @Id
    private UUID userId;

    @Id
    private UUID meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private JpaUserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meetingId")
    private JpaMeetingEntity meeting;
}
