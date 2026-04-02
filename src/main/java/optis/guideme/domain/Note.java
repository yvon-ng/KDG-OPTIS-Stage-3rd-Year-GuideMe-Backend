package optis.guideme.domain;

import java.util.List;

public class Note {
    private String title;
    private String description;
    private Visibility visibility;
    private Meeting meeting;
    private List<User> sharedUsers;

    public void publish(Visibility visibility) { // to share or make private
        this.visibility = visibility;
    }

    public void publish(Meeting meeting) { // to share with meeting attendees
        this.visibility = Visibility.MEETING_PARTICIPANTS;
        this.meeting = meeting;
    }

    public void share(User user) { // to share with user
        sharedUsers.add(user);
    }

    public void hide(User user) { // to revoke from user
        sharedUsers.remove(user);
    }
}
