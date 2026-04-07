package optis.guideme.domain.meetingRequest;

import org.springframework.util.Assert;

import java.util.UUID;

public record MeetingRequestId(UUID meetingRequestId) {
    public MeetingRequestId{
        Assert.notNull(meetingRequestId, "Error: UserId Can't be null");
    }
}
