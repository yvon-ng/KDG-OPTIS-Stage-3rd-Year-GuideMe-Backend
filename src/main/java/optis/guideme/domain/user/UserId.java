package optis.guideme.domain.user;

import org.springframework.util.Assert;

import java.util.UUID;

public record UserId(UUID userId) {
    public UserId {
        Assert.notNull(userId, "Error: UserId Can't be null");
    }

    public static UserId of(UUID uuid) {
        return new UserId(uuid);
    }
}
