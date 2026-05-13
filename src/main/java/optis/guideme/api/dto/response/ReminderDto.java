package optis.guideme.api.dto.response;

import java.util.UUID;

public record ReminderDto(UUID reminderId, UUID meetingId, int hoursBeforeMeeting) {

}