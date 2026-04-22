package optis.guideme.persistence.meeting;


import jakarta.persistence.*;
import lombok.Getter;
import optis.guideme.domain.meeting.Attendee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "meetings")
public class JpaMeetingEntity {
    @Id
    private UUID meetingId;
    private String referenceCode;
    private LocalDateTime startDateTime;
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<JpaAttendeeEntity> sharedUsers;
    private LocalDateTime endDateTime;
    private boolean occurred;
}
