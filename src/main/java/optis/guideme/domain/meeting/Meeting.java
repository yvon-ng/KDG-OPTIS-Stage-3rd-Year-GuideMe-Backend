package optis.guideme.domain.meeting;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.WrongInputException;
import optis.guideme.domain.user.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="meetings")
public class Meeting {
    // when created make reminder for all attendee
    @Id
    @GeneratedValue
    private UUID meetingId;
    private String name; //should be same as meetingrequest
    private String referenceCode; //should be same as meetingrequest
    private LocalDateTime startDateTime;
    @ElementCollection
    @CollectionTable(
            name = "attendees",
            joinColumns = @JoinColumn(name = "meeting_id")
    ) // atleast two; 1 manager 1 consultant !
    private List<Attendee> attendees = new ArrayList<>();
    private LocalDateTime endDateTime;
    private boolean occurred;


    public Meeting(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, List<Attendee> attendees) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.occurred = false;
        this.attendees = new ArrayList<>();
    }

    //constructor to create new Meeting after .accept() MeetingRequest
    public Meeting( String name, LocalDateTime startDateTime, LocalDateTime endDateTime,  String referenceCode,  List<Attendee> attendees) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.referenceCode = referenceCode;
        this.attendees = attendees;
        this.occurred = false;
    }

    public Quarter getQuarter() {
        return Quarter.fromMonth(startDateTime.getMonthValue());
    }

    public String getDisplayName() {
        return (name != null && !name.isBlank()) ? name : referenceCode;
    }

    //validate sharedUsers
    public void validateMinimumRoles(List<Attendee> attendees) {
        long managers = attendees.stream()
                .filter(attendee -> attendee.getRole() == Role.MANAGER)
                .count();
        long consultants = attendees.stream()
                .filter(attendee -> attendee.getRole() == Role.CONSULTANT)
                .count();
        if (attendees.size() < 2) {
            throw new WrongInputException("Minimum roles required");
        }

        if (managers < 1 || consultants < 1){
            throw new WrongInputException("Minimum roles required");
        }
    }


    public void assignReferenceCode(String referenceCode) { // use getter if already assigned
        if (this.referenceCode != null) {
            throw new WrongInputException("Reference code already assigned");
        }
        this.referenceCode = referenceCode;
    }

    public void assignAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }
}
