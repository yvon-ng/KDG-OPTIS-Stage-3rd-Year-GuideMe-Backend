package optis.guideme.api.mapper;

import optis.guideme.api.dto.request.AttendeeDto;
import optis.guideme.api.dto.request.MeetingDto;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.stereotype.Component;

@Component
public class MeetingMapper {
    private final AttendeeMapper mapper;

    public MeetingMapper(AttendeeMapper mapper) {
        this.mapper = mapper;
    }

    public MeetingDto toDto(Meeting meeting) {
        return new MeetingDto(
                meeting.getName(),
                meeting.getReferenceCode(),
                meeting.getStartDateTime(),
                meeting.getSharedUsers().stream().map(
                                attendee -> new AttendeeDto(
                                        attendee.getUser().getUserId(),
                                        attendee.getMeeting().getMeetingId()
                                )
                        )
                        .toList(),
                meeting.getEndDateTime(),
                meeting.isOccured()
        );
    }

    public Meeting toDomain(MeetingDto meetingDto) {
        return new Meeting(
            meetingDto.endDateTime(),
                meetingDto.name(),
                meetingDto.occured(),
                meetingDto.referenceCode(),
                meetingDto.sharedUsers().stream().map(attendeeDto -> mapper.toDomain(attendeeDto) ).toList(),
                meetingDto.startDateTime()
        );
    }
}
