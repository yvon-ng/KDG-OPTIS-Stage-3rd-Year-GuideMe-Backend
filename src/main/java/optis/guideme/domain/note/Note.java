package optis.guideme.domain.note;

import jakarta.persistence.*;
import lombok.Getter;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name="notes")
public class Note {

    @Id
    private UUID noteId;
    private String title;
    private String description;
    private Visibility visibility;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
    @ManyToMany
    @JoinTable(
            name = "shared_user_notes",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> sharedUsers;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    private User owner;

    public void publish(Visibility visibility) { // to share or make private
        this.visibility = visibility;
    }

    public void publish(Meeting meeting) { // to share with meeting attendees
        this.visibility = Visibility.MEETING_PARTICIPANTS;
        this.meeting = meeting;
    }

    public void share(User user) { // to share with user
        if (sharedUsers == null) {
            sharedUsers = new ArrayList<>();
        }
        sharedUsers.add(user);
    }

    public void hide(User user) { // to revoke from user
        sharedUsers.remove(user);
    }
}
