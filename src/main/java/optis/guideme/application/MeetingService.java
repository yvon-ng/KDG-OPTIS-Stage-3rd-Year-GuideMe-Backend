package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.mapper.request.NewMeetingMapper;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRepository;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final NewMeetingMapper mapper;
    private final UserRepository userRepository;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;

    public MeetingDto createNewMeeting(NewMeetingDto newMeetingDto) {
        Meeting meeting = mapper.toDomain(newMeetingDto);
        List<Attendee> attendees = newMeetingDto.attendees().stream()
                .map(a -> {
                    User user = userRepository.findById(a.userId()).orElseThrow(() -> new NotFoundException("User Not found with id " + a.userId()));
                    return new Attendee(a.role(), user);
                })
                .toList();

        meeting.validateMinimumRoles(attendees);
        meeting.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCodeFromMeeting(meeting));
        return mapper.toResponseDto(meetingRepository.save(meeting));
    }

    public List<MeetingDto> getAllMeetings() {
        return meetingRepository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    public List<MeetingDto> getAllMeetingsByUser(UUID userId) {
        return meetingRepository.findAllByAttendees_User_UserId(userId).stream().map(mapper::toResponseDto).toList();
    }
}
