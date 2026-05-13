package optis.guideme.api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReminderDto(UUID reminderId, String meetingName, LocalDateTime scheduledAt, int hoursBeforeMeeting) {

}