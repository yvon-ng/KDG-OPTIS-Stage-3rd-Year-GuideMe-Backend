package optis.guideme.api.mapper.request;

import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.user.User;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class NewMeetingMapper {
    private final NewAttendeeMapper mapper;
    private final UserRepository userRepository;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;

    public NewMeetingMapper(NewAttendeeMapper mapper, UserRepository userRepository, DefaultReferenceCodeGenerator defaultReferenceCodeGenerator) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.defaultReferenceCodeGenerator = defaultReferenceCodeGenerator;
    }

    public Meeting toDomain(NewMeetingDto newMeetingDto) {
        Meeting meeting = new Meeting(
                newMeetingDto.endDateTime(),
                newMeetingDto.name(),
                false,
                newMeetingDto.startDateTime()
        );
        newMeetingDto.sharedUsers().forEach(a -> {
            User user = userRepository.findById(a.userId()).orElseThrow();
            meeting.addAttendee(user, a.role());
        });
        meeting.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCodeFromMeeting(meeting));
        return meeting;
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
