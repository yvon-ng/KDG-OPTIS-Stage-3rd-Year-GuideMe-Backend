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

    public MeetingRequest(LocalDateTime endDateTime, boolean isAllowed, String name, UUID previousRequestId, int recurrenceIntervalInMonths, String referenceCode, @NonNull User requestedBy, List<User> requestFor, LocalDateTime startDateTime, Status status) {
        this.endDateTime = endDateTime;
        this.isAllowed = isAllowed;
        this.name = name;
        this.previousRequestId = previousRequestId;
        this.recurrenceIntervalInMonths = recurrenceIntervalInMonths;
        this.referenceCode = referenceCode;
        this.requestedBy = requestedBy;
        this.requestFor = requestFor;
        this.startDateTime = startDateTime;
        this.status = status;
    }

    //todo probeer zonder simplename en me instance of
    public boolean isAllowed() {
        if (requestedBy instanceof Manager || requestFor.stream().anyMatch(User -> User.getClass().getSimpleName().equalsIgnoreCase("MANAGER"))) {
            isAllowed = true;
        } else {
            isAllowed = false;
        }
        return isAllowed;
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
