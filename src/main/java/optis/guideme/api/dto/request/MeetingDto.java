package optis.guideme.api.dto.request;

import optis.guideme.domain.meeting.Meeting;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public record MeetingDto(String name, String referenceCode, LocalDateTime startDateTime, List<AttendeeDto> sharedUsers, LocalDateTime endDateTime, boolean occured) {

}
