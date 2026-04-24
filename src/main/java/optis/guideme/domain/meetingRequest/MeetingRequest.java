package optis.guideme.domain.meetingRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import optis.guideme.domain.exception.WrongInputException;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.Manager;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;

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
    private int recurrenceIntervalInMonths;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name="requested_by")
    @NonNull
    private User requestedBy;
    @ManyToMany
    @JoinTable(
            name = "meeting_request_participants",
            joinColumns = @JoinColumn(name="meeting_request_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> requestFor;
    private UUID previousRequestId;
    private boolean isAllowed = false;

    public MeetingRequest(LocalDateTime endDateTime, boolean isAllowed, String name, UUID previousRequestId, int recurrenceIntervalInMonths, @NonNull User requestedBy, List<User> requestFor, LocalDateTime startDateTime, Status status) {
        this.endDateTime = endDateTime;
        this.isAllowed = isAllowed;
        this.status = status;
        this.startDateTime = startDateTime;
        this.requestFor = requestFor;
        this.requestedBy = requestedBy;
        if (!(requestedBy instanceof Manager ||
                requestFor.stream().anyMatch(user -> user instanceof Manager))) {
            throw new WrongInputException("At least one manager required");
        }
        this.recurrenceIntervalInMonths = recurrenceIntervalInMonths;
        this.previousRequestId = previousRequestId;
        this.name = name;
    }

    public Meeting accept(){
        return new Meeting();
    }

    public boolean isRecurrent(){
        return recurrenceIntervalInMonths != 0;
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
