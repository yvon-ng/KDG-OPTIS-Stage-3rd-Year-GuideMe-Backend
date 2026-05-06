package optis.guideme.domain.note;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.api.dto.UpdateNoteDto;
import optis.guideme.domain.exception.ForbiddenException;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
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
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode description;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    private UUID meetingId;
    @ElementCollection
    @CollectionTable(
            name = "shared_user_notes",
            joinColumns = @JoinColumn(name = "note_id")
    )
    @Column(name = "user_id")
    private List<UUID> sharedUsers;
    private UUID owner;
    private LocalDateTime creationTime;

    //For adding new notes only
    public Note(String title, JsonNode description, Visibility visibility, UUID meetingId, List<UUID> sharedUsers) {
        this.title = title;
        this.description = description;
        this.visibility = visibility;
        this.meetingId = meetingId;
        this.sharedUsers = (sharedUsers != null) ? new ArrayList<>(sharedUsers) : new ArrayList<>();
        this.creationTime = LocalDateTime.now();
    }

    public void publish(Visibility visibility) { // to share or make private
        this.visibility = visibility;
    }

    public void publish(UUID meetingId) { // to share with meetingId attendees
        this.visibility = Visibility.MEETING_PARTICIPANTS;
        this.meetingId = meetingId;
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

    public void update(UUID ownerId, UpdateNoteDto updateNoteDto) {
        if (!owner.equals(ownerId)) {
            throw new ForbiddenException("No rights to update this note");
        }

        this.title = updateNoteDto.title();
        this.description = updateNoteDto.description();
        this.visibility = updateNoteDto.visibility();
        this.meetingId = updateNoteDto.meetingId();
        this.sharedUsers = updateNoteDto.sharedUsers();
    }

    public void assignOwner(UUID userId) {
        this.owner = userId;
    }
}
