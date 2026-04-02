package optis.guideme.domain.user;

public class Manager extends User {
    public void updateMeeting(){} // for adjusting a meeting
    public void updateMeetingRequest(){} // for rescheduling a meeting from a consultant
    public void updateConsultant(){} // for updating consultant details

    public Manager(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
}
