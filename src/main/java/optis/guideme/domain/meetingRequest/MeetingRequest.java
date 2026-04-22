package optis.guideme.domain.meetingRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.WrongInputException;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.User;
import optis.guideme.domain.user.UserId;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MeetingRequest {
    private MeetingRequestId meetingRequestId;
    private String name;
    private String referenceCode;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int recurrenceIntervalInMonths;
    private Status status;
    private UserId requestedBy;
    private List<UserId> requestFor;
    private MeetingRequestId previousRequestId;
    private boolean isAllowed = false;

        public MeetingRequest(LocalDateTime endDateTime, boolean isAllowed, MeetingRequestId meetingRequestId, String name, MeetingRequestId previousRequestId, int recurrenceIntervalInMonths, String referenceCode, UserId requestedBy, List<UserId> requestFor, LocalDateTime startDateTime, Status status) {
        this.endDateTime = endDateTime;
        this.isAllowed = isAllowed;
        this.meetingRequestId = meetingRequestId;
        this.name = name;
        this.previousRequestId = previousRequestId;
        this.recurrenceIntervalInMonths = recurrenceIntervalInMonths;
        this.referenceCode = referenceCode;
        this.requestedBy = requestedBy;
        this.requestFor = requestFor;
        this.startDateTime = startDateTime;
        this.status = status;
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
