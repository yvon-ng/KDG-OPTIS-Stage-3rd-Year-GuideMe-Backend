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
    private UUID requestedBy;

    @ElementCollection
    @CollectionTable(name = "meeting_request_participants", joinColumns = @JoinColumn(name = "meeting_request_id"))
    @Column(name = "user_id")
    private List<UUID> requestFor;
    private UUID previousRequestId;
    private boolean isAllowed = false;

    // only for fresh (not counter request) new meeting requests
    public MeetingRequest(LocalDateTime endDateTime, String name, int recurrencePerYear, List<UUID> requestFor, LocalDateTime startDateTime) {
        this.endDateTime = endDateTime;
        this.status = Status.PENDING;
        this.startDateTime = startDateTime;
        this.requestFor = requestFor;
        this.recurrencePerYear = recurrencePerYear;
        this.previousRequestId = null;
        this.name = name;
    }

    //constructor for counter request
    public MeetingRequest(String name, String referenceCode, LocalDateTime startDateTime, LocalDateTime endDateTime, int recurrencePerYear, UUID requestedBy, List<UUID> requestFor, UUID previousRequestId) {
        this.name = name;
        this.referenceCode = referenceCode;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.recurrencePerYear = recurrencePerYear;
        this.status = Status.PENDING;
        this.requestedBy = requestedBy;
        this.requestFor = requestFor;
        this.previousRequestId = previousRequestId;
    }

    public void assignRequestedBy(UUID userId){
        this.requestedBy = userId;
    }

    public Meeting accept(List<Attendee> attendees) {
        this.status = Status.ACCEPTED;
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

    public void decline() {
        this.status = Status.DECLINED;
    }

    public MeetingRequest reschedule(LocalDateTime newStartDateTime, LocalDateTime newEndDateTime, int newRecurrencePerYear, UUID newRequestedBy, List<UUID> newRequestFor){
        this.status = Status.COUNTER_REQUESTED;
        return new MeetingRequest(this.name, this.referenceCode, newStartDateTime, newEndDateTime, newRecurrencePerYear, newRequestedBy, newRequestFor, this.meetingRequestId);
    }
}
