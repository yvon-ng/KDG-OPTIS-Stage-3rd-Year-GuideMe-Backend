package optis.guideme.persistence.meeting;


import jakarta.persistence.*;
import lombok.Getter;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meeting.MeetingId;
import optis.guideme.domain.user.UserId;
import optis.guideme.persistence.meeting.attendee.JpaAttendeeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "meetings")
public class JpaMeetingEntity {
    @Id
    private UUID meetingId;
    private String name;
    private String referenceCode;
    private LocalDateTime startDateTime;
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<JpaAttendeeEntity> sharedUsers;
    private LocalDateTime endDateTime;
    private boolean occurred;

    public static JpaMeetingEntity fromDomain(Meeting meeting) {
        JpaMeetingEntity entity = new JpaMeetingEntity();
        entity.meetingId = meeting.getMeetingId().meetingId();
        entity.endDateTime = meeting.getEndDateTime();
        entity.name = meeting.getName();
        entity.occurred = meeting.isOccured();
        entity.referenceCode = meeting.getReferenceCode();
        if (meeting.getSharedUsers() != null) {
            entity.sharedUsers = meeting.getSharedUsers().stream()
                    .map(attendee -> {
                        JpaAttendeeEntity a = new JpaAttendeeEntity();

                        UUID userId = attendee.getUserId().userId();
                        UUID meetingId = attendee.getMeetingId().meetingId();

                        a.setUserId(userId);
                        a.setMeetingId(meetingId);

                        return a;
                    })
                    .toList();
        }
        entity.startDateTime = meeting.getStartDateTime();
        return entity;
    }

    public static Meeting toDomain(JpaMeetingEntity meetingEntity) {
                MeetingId id = MeetingId.of(meetingEntity.getMeetingId());

        List<Attendee> attendees = meetingEntity.getSharedUsers() == null
                ? new ArrayList<>()
                : meetingEntity.getSharedUsers().stream()
                .map(a -> new Attendee(
                        UserId.of(a.getUserId()),
                        MeetingId.of(a.getMeetingId())
                ))
                .toList();

                return new Meeting(
                        id,
                        meetingEntity.getEndDateTime(),
                        meetingEntity.getName(),
                        meetingEntity.isOccurred(),
                        meetingEntity.getReferenceCode(),
                        attendees,
                        meetingEntity.getStartDateTime()
                );
    }
}
