package optis.guideme.domain.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name="notes")
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue
    private UUID noteId;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    private UUID meeting;
    @ElementCollection
    @CollectionTable(
            name = "shared_user_notes",
            joinColumns = @JoinColumn(name = "note_id")
    )
    @Column(name = "user_id")
    private List<UUID> sharedUsers;
    private UUID owner;

    public Note(String title, String description, Visibility visibility, UUID meeting, List<UUID> sharedUsers) {
        this.title = title;
        this.description = description;
        this.visibility = visibility;
        this.meeting = meeting;
        this.sharedUsers = (sharedUsers != null) ? new ArrayList<>(sharedUsers) : new ArrayList<>();
    }

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

    public void assignOwner(UUID userId) {
        this.owner = userId;
    }
}
