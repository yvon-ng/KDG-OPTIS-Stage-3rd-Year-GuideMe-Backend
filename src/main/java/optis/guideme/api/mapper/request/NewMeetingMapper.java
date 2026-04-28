package optis.guideme.api.mapper.request;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.user.User;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewMeetingMapper {
    private final NewAttendeeMapper mapper;

    public Meeting toDomain(NewMeetingDto newMeetingDto) {
        return new Meeting(
                newMeetingDto.name(),
                newMeetingDto.endDateTime(),
                newMeetingDto.startDateTime()
        );
    }
    public MeetingDto toResponseDto(Meeting meeting) {
        return new MeetingDto(
                meeting.getName(),
                meeting.getReferenceCode(),
                meeting.getStartDateTime(),
                meeting.getAttendees().stream().map(
                                mapper::toResponseDto
                        )
                        .toList(),
                meeting.isOccured()
        );
    }

}
