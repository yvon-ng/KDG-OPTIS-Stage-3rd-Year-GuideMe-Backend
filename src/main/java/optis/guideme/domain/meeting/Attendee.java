package optis.guideme.domain.meeting;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import optis.guideme.domain.user.Role;

import java.util.UUID;

@Getter
@Embeddable
public class Attendee {

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected Attendee() {}

    public Attendee(UUID userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
}