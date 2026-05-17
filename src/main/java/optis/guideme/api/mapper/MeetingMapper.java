package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Component
public class MeetingMapper {
    private final AttendeeMapper mapper;

    public Meeting toDomain(NewMeetingDto newMeetingDto) {
        LocalDateTime localStartDateTime = LocalDateTime.ofInstant(newMeetingDto.startDateTime(), ZoneId.of("Europe/Brussels"));
        LocalDateTime localEndDateTime = LocalDateTime.ofInstant(newMeetingDto.endDateTime(), ZoneId.of("Europe/Brussels"));
        return new Meeting(
                newMeetingDto.name(),
                localStartDateTime,
                localEndDateTime
        );
    }
    public MeetingDto toResponseDto(Meeting meeting) {
        return new MeetingDto(
                meeting.getMeetingId(),
                meeting.getName(),
                meeting.getReferenceCode(),
                meeting.getStartDateTime(),
                meeting.getAttendees().stream().map(
                                mapper::toResponseDto
                        ).toList(),
                meeting.getEndDateTime(),
                meeting.isOccurred()
        );
    }

}
