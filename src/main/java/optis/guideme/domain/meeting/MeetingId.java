package optis.guideme.domain.meeting;

import org.springframework.util.Assert;

import java.util.UUID;

public record MeetingId(UUID meetingId){
    public MeetingId{
        Assert.notNull(meetingId, "Error: UserId Can't be null");
    }
}
