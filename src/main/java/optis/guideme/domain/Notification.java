package optis.guideme.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="notifications")
@Getter
public class Notification {
    @Id
    @GeneratedValue
    private UUID notificationId;
    private UUID receiver;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private boolean read;
    private LocalDateTime sentAt;

    public Notification(UUID receiver, String title, String description, NotificationType notificationType) {
        this.receiver = receiver;
        this.title = title;
        this.description = description;
        this.notificationType = notificationType;
        this.read = false;
        this.sentAt = LocalDateTime.now();
    }

    public void markAsRead(){
        this.read = true;
    }
}
