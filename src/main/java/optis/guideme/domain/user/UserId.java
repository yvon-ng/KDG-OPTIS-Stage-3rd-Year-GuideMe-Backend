package optis.guideme.domain.user;

import org.springframework.util.Assert;

import java.util.UUID;

public record UserId(UUID id) {
    public UserId {
        Assert.notNull(id, "Error: UserId Can't be null");
    }
}
