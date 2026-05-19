package optis.guideme.api.dto.response;

import optis.guideme.domain.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReminderDto(UUID id, String meetingName, int hoursBeforeMeeting, LocalDateTime sentAt, NotificationType type) {

}