package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.ReminderDto;
import optis.guideme.application.MeetingService;
import optis.guideme.domain.meeting.Reminder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReminderMapper {
    private final MeetingService meetingService;

    public ReminderDto toResponseDto(Reminder reminder) {
        return new ReminderDto(
                reminder.getReminderId(),
                meetingService.get(reminder.getMeetingId()).getDisplayName(),
                reminder.getScheduledAt(),
                Reminder.EMAIL_REMINDER_HOURS_BEFORE_MEETING
        );
    }
}
