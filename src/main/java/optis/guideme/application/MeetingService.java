package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.mapper.request.NewAttendeeMapper;
import optis.guideme.api.mapper.request.NewMeetingMapper;
import optis.guideme.domain.exception.ForbiddenException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.repository.MeetingRepository;
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
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final NewAttendeeMapper attendeeMapper;
    private final UserService userService;

    public MeetingDto createNewMeeting(NewMeetingDto newMeetingDto) {
        Meeting meeting = meetingMapper.toDomain(newMeetingDto);
        List<Attendee> attendees = newMeetingDto.attendees().stream()
                .map(a -> new Attendee(a.userId(), a.role()))
                .toList();

        meeting.validateMinimumRoles(attendees);
        meeting.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCodeFromMeeting(meeting));
        return meetingMapper.toResponseDto(meetingRepository.save(meeting));
    }

    public List<MeetingDto> getAllMeetings() {
        return meetingRepository.findAll().stream().map(meetingMapper::toResponseDto).toList();
    }

    public List<MeetingDto> getAllMeetingsByUser(UUID userId) {
        return meetingRepository.findAllByAttendeesUserId(userId).stream().map(meetingMapper::toResponseDto).toList();
    }

//    public List<AttendeeDto> getMeetingAttendees(UUID userId, UUID meetingId) {
//        boolean isParticipant = attendeeRepository.existsByMeeting_MeetingIdAndUser_UserId(meetingId, userId);
//
//        if (!isParticipant) {
//            throw new ForbiddenException("you're not part of this meeting");
//        }
//
//        return attendeeRepository.findOtherAttendees(meetingId, userId).stream().map(attendeeMapper::toResponseDto).toList();
//    }

    public List<AttendeeDto> getMeetingAttendees(UUID userId, UUID meetingId){
        Meeting meeting = meetingRepository.findMeetingByMeetingId(meetingId);
        boolean isParticipant = meeting.getAttendees().stream()
                .anyMatch(a -> a.getUserId().equals(userId));

        if (!isParticipant) {
            throw new ForbiddenException("you're not part of this meeting");
        }

        return meeting.getAttendees().stream()
                .filter(a -> !a.getUserId().equals(userId))
                .map(attendeeMapper::toResponseDto)
                .toList();
    }

    public MeetingDto getNextMeeting() {
        return meetingMapper.toResponseDto(meetingRepository.findFirstByStartDateTimeAfterOrderByStartDateTimeAsc(LocalDateTime.now()).orElseThrow());
    }

    public void save(Meeting meeting) {
        meetingRepository.save(meeting);
    }
}
