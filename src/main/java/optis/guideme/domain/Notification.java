package optis.guideme.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private String title;
    private String description;
    private boolean isRead;
    private LocalDateTime receivedAt;
    private UUID receiver;
}
