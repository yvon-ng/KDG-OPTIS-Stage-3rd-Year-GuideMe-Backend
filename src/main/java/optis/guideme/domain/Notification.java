package optis.guideme.domain;

import optis.guideme.domain.user.UserId;

import java.time.LocalDateTime;

public class Notification {
    private String title;
    private String description;
    private boolean isRead;
    private LocalDateTime receivedAt;
    private UserId receiver;
}
