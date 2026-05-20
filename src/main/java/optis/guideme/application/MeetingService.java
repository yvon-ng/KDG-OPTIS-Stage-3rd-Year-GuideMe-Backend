package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.api.dto.response.MeetingContextDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.mapper.AttendeeMapper;
import optis.guideme.api.mapper.MeetingMapper;
import optis.guideme.api.mapper.MeetingContextMapper;
import optis.guideme.domain.exception.ForbiddenException;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meeting.Reminder;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final AttendeeMapper attendeeMapper;
    private final MeetingContextMapper meetingContextMapper;
    private final EmailService emailService;
    private final ReminderService reminderService;

    private Meeting getMeeting(UUID meetingId){
        return meetingRepository.findById(meetingId).orElseThrow(() -> new NotFoundException("Meeting Not Found with Id: " + meetingId));
    }

    public MeetingDto createNewMeeting(UUID userId, NewMeetingDto newMeetingDto) {
        Meeting meeting = meetingMapper.toDomain(userId, newMeetingDto);

        meeting.validateMinimumRoles(meeting.getAttendees());
        meeting.assignAttendees(meeting.getAttendees()); // can be removed? TODO
        meeting.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCodeFromMeeting(meeting));

        // send emails 1 hour before meeting
        meeting.getAttendees().forEach(a -> emailService.sendEmailReminder(a.getUserId(), meeting));
        meetingRepository.save(meeting); //meeting must be saved first so meetingId can be generated and used in reminders
        List<Reminder> reminders = meeting.getAttendees().stream().map(a -> new Reminder(a.getUserId(), meeting)).toList();

        reminderService.saveAll(reminders);
        return meetingMapper.toResponseDto(meeting);
    }

    public List<MeetingDto> getAllMeetings() {
        return meetingRepository.findAllByOrderByStartDateTimeDesc().map(meetingMapper::toResponseDto).toList();
    }

    public List<MeetingDto> getAllMeetingsByUser(UUID userId, Integer quarter, Integer year, List<UUID> personIds) {
        try (Stream<Meeting> meetingStream = meetingRepository.findAllByAttendeesUserId(userId)) {
            return meetingStream.filter(meeting -> matchesFilters(meeting, year, quarter, personIds)).map(meetingMapper::toResponseDto).toList();
        }
    }

    private boolean matchesFilters(Meeting meeting, Integer year, Integer quarter, List<UUID> personIds) {

        if (year != null && meeting.getStartDateTime().getYear() != year) {
            return false;
        }
        if (quarter != null) {
            int meetingQuarter = meeting.getQuarter().getValue();
            if (meetingQuarter != quarter) {
                return false;
            }
        }
        if (personIds != null && !personIds.isEmpty()) {
            return meeting.getAttendees().stream().anyMatch(attendee -> personIds.contains(attendee.getUserId()));
        }
        return true;
    }

    public List<AttendeeDto> getMeetingAttendees(UUID userId, UUID meetingId) {
        Meeting meeting = getMeeting(meetingId);
        boolean isParticipant = meeting.getAttendees().stream().anyMatch(a -> a.getUserId().equals(userId));
        if (!isParticipant) {
            throw new ForbiddenException("you're not part of this meeting");
        }
        return meeting.getAttendees().stream().filter(a -> !a.getUserId().equals(userId)).map(attendeeMapper::toResponseDto).toList();
    }

    public MeetingContextDto getNextAndLastMeeting(UUID userId) {
        MeetingDto nextMeeting = meetingRepository.findFirstByAttendees_UserIdAndStartDateTimeAfterOrderByStartDateTimeAsc(userId, LocalDateTime.now()).map(meetingMapper::toResponseDto).orElse(null);
        MeetingDto latestMeeting = meetingRepository.findFirstByAttendees_UserIdAndStartDateTimeBeforeOrderByStartDateTimeDesc(userId, LocalDateTime.now()).map(meetingMapper::toResponseDto).orElse(null);
        return meetingContextMapper.toContextDto(nextMeeting, latestMeeting);
    }

    public void save(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    public MeetingDto get(UUID userId, UUID meetingId) {
        Meeting meeting = getMeeting(meetingId);
        if (meeting.getAttendees().stream().noneMatch(a -> a.getUserId().equals(userId))) throw new ForbiddenException("You're not part of this meeting");
        return meetingMapper.toResponseDto(meeting);
    }
}
