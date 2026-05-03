package optis.guideme.domain.meeting;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.WrongInputException;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;

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
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendee> attendees =  new ArrayList<>(); // atleast two; 1 manager 1 consultant !
    private LocalDateTime endDateTime;
    private boolean occured;


    public Meeting(String name, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.occured = false;
        this.attendees = new ArrayList<>();
    }


    public Quarter getQuarter() {
        return Quarter.fromMonth(startDateTime.getMonthValue());
    }

    public String getDisplayName() {
        return (name != null && !name.isBlank()) ? name : referenceCode;
    }

    //add attendee to meeting
    public void addAttendee(User user, Role role) {
        Attendee attendee = new Attendee(role, user);
        this.attendees.add(attendee);
        attendee.addAttendeeToMeeting(this);
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
}
