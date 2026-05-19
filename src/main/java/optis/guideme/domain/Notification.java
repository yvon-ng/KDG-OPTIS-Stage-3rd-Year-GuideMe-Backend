package optis.guideme.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private boolean read;
    private boolean sent;
    private LocalDateTime sentAt;
}
