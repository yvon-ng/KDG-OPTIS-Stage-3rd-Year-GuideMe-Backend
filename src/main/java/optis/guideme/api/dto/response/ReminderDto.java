package optis.guideme.api.dto.response;

import java.util.UUID;

public record ReminderDto(UUID id, String meetingName, int hoursBeforeMeeting, String type) {

}