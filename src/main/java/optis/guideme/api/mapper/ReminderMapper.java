package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.application.MeetingService;
import optis.guideme.domain.NotificationType;
import optis.guideme.domain.meeting.Reminder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReminderMapper {
    private final MeetingService meetingService;

    public ReminderDto toResponseDto(Reminder reminder) {
        return new ReminderDto(
                reminder.getReminderId(),
                meetingService.getMeeting(reminder.getMeetingId()).getDisplayName(),
                Reminder.EMAIL_REMINDER_HOURS_BEFORE_MEETING,
                reminder.getSentAt(),
                NotificationType.REMINDER
        );
    }
}
