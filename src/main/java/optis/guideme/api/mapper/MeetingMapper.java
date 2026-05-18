package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MeetingMapper {
    private final AttendeeMapper mapper;

    public Meeting toDomain(UUID userId, NewMeetingDto newMeetingDto) {
        LocalDateTime localStartDateTime = LocalDateTime.ofInstant(newMeetingDto.startDateTime(), ZoneId.of("Europe/Brussels"));
        LocalDateTime localEndDateTime = LocalDateTime.ofInstant(newMeetingDto.endDateTime(), ZoneId.of("Europe/Brussels"));
        List<Attendee> attendees = new ArrayList<>(newMeetingDto.attendees().stream().map(mapper::toDomain).toList());
        attendees.add(mapper.toDomain(userId));

        return new Meeting(
                newMeetingDto.name(),
                localStartDateTime,
                localEndDateTime,
                attendees
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
