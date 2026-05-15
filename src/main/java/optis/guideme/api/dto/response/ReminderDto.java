package optis.guideme.api.dto.response;

import java.util.UUID;

public record ReminderDto(UUID reminderId, String meetingName, int hoursBeforeMeeting) {

}