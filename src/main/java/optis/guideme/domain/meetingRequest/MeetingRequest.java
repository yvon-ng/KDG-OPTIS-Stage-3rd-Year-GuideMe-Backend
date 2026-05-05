package optis.guideme.domain.meetingRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.WrongInputException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="meeting_requests")
public class MeetingRequest {
    @Id
    @GeneratedValue
    private UUID meetingRequestId;
    private String name;
    private String referenceCode;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int recurrencePerYear;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "requested_by")
    private UUID requestedBy;

    @ElementCollection
    @CollectionTable(name = "meeting_request_participants", joinColumns = @JoinColumn(name = "meeting_request_id"))
    @Column(name = "user_id")
    private List<UUID> requestFor;
    private UUID previousRequestId;
    private boolean isAllowed = false;

    public MeetingRequest(LocalDateTime endDateTime, String name, int recurrencePerYear, List<UUID> requestFor, LocalDateTime startDateTime) {
        this.endDateTime = endDateTime;
        this.status = Status.PENDING;
        this.startDateTime = startDateTime;
        this.requestFor = requestFor;
        this.recurrencePerYear = recurrencePerYear;
        this.previousRequestId = null;
        this.name = name;
    }

    public void assignRequestedBy(UUID userId){
        this.requestedBy = userId;
    }

    public Meeting accept(List<Attendee> attendees) {
//          return new Meeting(
//               meetingRequest.name,
//               meetingRequest.startDateTime,
//               meetingRequest.endDateTime,
//               meetingRequest.referenceCode,
//
//               );
//        );
        return new Meeting(this.name, this.startDateTime, this.endDateTime, this.referenceCode, attendees);
    }

    public boolean isRecurrent(){
        return recurrencePerYear != 0;
    }

    public void assignReferenceCode(String referenceCode) { // use getter if already assigned
        if (this.referenceCode != null) {
            throw new WrongInputException("Reference code already assigned");
        }
        this.referenceCode = referenceCode;
    }

    public String getDisplayName() {
        return (name != null && !name.isBlank()) ? name : referenceCode;
    }

}
