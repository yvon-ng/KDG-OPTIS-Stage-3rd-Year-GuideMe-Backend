package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.mapper.request.NewAttendeeMapper;
import optis.guideme.api.mapper.request.NewMeetingMapper;
import optis.guideme.domain.exception.ForbiddenException;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.user.User;
import optis.guideme.repository.AttendeeRepository;
import optis.guideme.repository.MeetingRepository;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final NewMeetingMapper meetingMapper;
    private final UserRepository userRepository;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final NewAttendeeMapper attendeeMapper;
    private final AttendeeRepository attendeeRepository;

    public MeetingDto createNewMeeting(NewMeetingDto newMeetingDto) {
        Meeting meeting = meetingMapper.toDomain(newMeetingDto);
        List<Attendee> attendees = newMeetingDto.attendees().stream()
                .map(a -> {
                    User user = userRepository.findById(a.userId()).orElseThrow(() -> new NotFoundException("User Not found with id " + a.userId()));
                    return new Attendee(user.getRole(), user);
                })
                .toList();

        meeting.validateMinimumRoles(attendees);
        meeting.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCodeFromMeeting(meeting));
        return meetingMapper.toResponseDto(meetingRepository.save(meeting));
    }

    public List<MeetingDto> getAllMeetings() {
        return meetingRepository.findAll().stream().map(meetingMapper::toResponseDto).toList();
    }

    public List<MeetingDto> getAllMeetingsByUser(UUID userId) {
        return meetingRepository.findAllByAttendees_User_UserId(userId).stream().map(meetingMapper::toResponseDto).toList();
    }

    public List<AttendeeDto> getMeetingAttendees(UUID userId, UUID meetingId) {
        boolean isParticipant = attendeeRepository.existsByMeeting_MeetingIdAndUser_UserId(meetingId, userId);

        if (!isParticipant) {
            throw new ForbiddenException("you're not part of this meeting");
        }

        return attendeeRepository.findOtherAttendees(meetingId, userId).stream().map(attendeeMapper::toResponseDto).toList();
    }

    public MeetingDto getNextMeeting() {
        return meetingMapper.toResponseDto(meetingRepository.findFirstByStartDateTimeAfterOrderByStartDateTimeAsc(LocalDateTime.now()).orElseThrow());
    }
}
