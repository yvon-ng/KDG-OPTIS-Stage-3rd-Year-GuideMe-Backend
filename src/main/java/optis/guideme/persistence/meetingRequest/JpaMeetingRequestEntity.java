package optis.guideme.persistence.meetingRequest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.meetingRequest.MeetingRequestId;
import optis.guideme.domain.meetingRequest.Status;
import optis.guideme.domain.user.UserId;
import optis.guideme.persistence.user.JpaUserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "meeting_requests")
public class JpaMeetingRequestEntity {
    @Id
    private UUID meetingRequestId;
    private String referenceCode;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int recurrenceIntervalInMonths;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne //one meetingrequest is made by one user
    @JoinColumn(name="requestedBy", nullable = false
    )
    @NonNull
    private JpaUserEntity requestedBy;
    @ManyToMany
    @JoinTable(
            name="meeting_requests_participants",
            joinColumns = @JoinColumn(name = "meeting_request_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<JpaUserEntity> requestFor;
    private boolean isAllowed = false;

    public static JpaMeetingRequestEntity fromDomain(MeetingRequest meetingRequest) {
            JpaMeetingRequestEntity entity = new JpaMeetingRequestEntity();

            entity.meetingRequestId = meetingRequest.getMeetingRequestId().meetingRequestId();
            entity.referenceCode = meetingRequest.getReferenceCode();
            entity.startDateTime = meetingRequest.getStartDateTime();
            entity.endDateTime = meetingRequest.getEndDateTime();
            entity.recurrenceIntervalInMonths = meetingRequest.getRecurrenceIntervalInMonths();
            entity.status = meetingRequest.getStatus();
            entity.isAllowed = meetingRequest.isAllowed();

            if (meetingRequest.getRequestedBy() != null) {
                JpaUserEntity requestedByEntity = new JpaUserEntity();
                requestedByEntity.setUserId(meetingRequest.getRequestedBy().userId());
                entity.requestedBy = requestedByEntity;
            }


            if (meetingRequest.getRequestFor() != null) {
                entity.requestFor = meetingRequest.getRequestFor().stream()
                        .map(user -> {
                            JpaUserEntity userEntity = new JpaUserEntity();
                            userEntity.setUserId(user.userId());
                            return userEntity;
                        })
                        .toList();
            }

            return entity;
    }

    public static MeetingRequest toDomain(JpaMeetingRequestEntity entity) {
//        if (entity.requestedBy != null)  {
//            UserId requestedBy = UserId.of(entity.getRequestedBy().getUserId());
//        }
        return new MeetingRequest(
                entity.getEndDateTime(),
                entity.isAllowed(),
                new MeetingRequestId(entity.getMeetingRequestId()),
                entity.getReferenceCode(),
                null,
                entity.getRecurrenceIntervalInMonths(),
                entity.getReferenceCode(),
                UserId.of(entity.requestedBy.getUserId()),
                entity.getRequestFor() != null ? entity.getRequestFor().stream().map(user -> UserId.of(user.getUserId())).toList(): List.of(),
                entity.getStartDateTime(),
                entity.getStatus()
        );
    }
}
