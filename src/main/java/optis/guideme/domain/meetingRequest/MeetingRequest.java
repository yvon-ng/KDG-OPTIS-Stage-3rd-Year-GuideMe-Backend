package optis.guideme.domain.meetingRequest;

import lombok.Getter;
import optis.guideme.domain.exception.WrongInputException;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MeetingRequest {
    private String name;
    private String referenceCode;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int recurrenceIntervalInMonths;
    private Status status;
    private User requestedBy;
    private List<User> requestFor;
    private MeetingRequestId previousRequestId;

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
