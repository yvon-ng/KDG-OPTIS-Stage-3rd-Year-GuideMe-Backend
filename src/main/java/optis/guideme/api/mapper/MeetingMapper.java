package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MeetingMapper {
    private final AttendeeMapper mapper;

    public Meeting toDomain(NewMeetingDto newMeetingDto) {
        return new Meeting(
                newMeetingDto.name(),
                newMeetingDto.endDateTime(),
                newMeetingDto.startDateTime()
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
