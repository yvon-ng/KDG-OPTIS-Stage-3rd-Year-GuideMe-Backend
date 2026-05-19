package optis.guideme.api.dto.response;

import optis.guideme.domain.NotificationType;

import java.util.UUID;

public record ReminderDto(UUID id, String meetingName, int hoursBeforeMeeting, NotificationType type) {

}