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

    private UUID meeting;
    private List<UUID> sharedUsers;
    private UUID owner;

    public void publish(Visibility visibility) { // to share or make private
        this.visibility = visibility;
    }

    public void publish(UUID meetingId) { // to share with meeting attendees
        this.visibility = Visibility.MEETING_PARTICIPANTS;
        this.meeting = meetingId;
        //ATTENDEES SHOULD BE ADDED TO SHARED USER LIST VIA SERVICE
    }

    public void share(UUID userId) { // to share with user
        if (sharedUsers == null) {
            sharedUsers = new ArrayList<>();
        }
        sharedUsers.add(userId);
    }

    public void hide(UUID userId) { // to revoke from user
        if (sharedUsers != null) {
            sharedUsers.remove(userId);
        }
    }
}
