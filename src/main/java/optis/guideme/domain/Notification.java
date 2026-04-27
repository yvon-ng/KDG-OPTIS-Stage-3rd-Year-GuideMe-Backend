package optis.guideme.domain;

import optis.guideme.domain.user.User;

import java.time.LocalDateTime;

public class Notification {
    private String title;
    private String description;
    private boolean isRead;
    private LocalDateTime receivedAt;
    private User receiver;
}
