package optis.guideme.api.dto;

import optis.guideme.domain.meeting.Meeting;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public record MeetingDto(String name, String referenceCode, LocalDateTime startDateTime, List<AttendeeDto> sharedUsers, LocalDateTime endDateTime, boolean occured) {

    public static @Nullable MeetingDto fromDomain(Meeting meeting) {
        return new MeetingDto(
                meeting.getName(),
                meeting.getReferenceCode(),
                meeting.getStartDateTime(),
                meeting.getSharedUsers().stream().map(
                        attendee -> new AttendeeDto(
                                attendee.getUserId().userId(),
                                attendee.getMeetingId().meetingId()
                        )
                )
                        .toList(),
                meeting.getEndDateTime(),
                meeting.isOccured()
        );
    }
}
