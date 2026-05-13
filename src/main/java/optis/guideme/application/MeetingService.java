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
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final AttendeeMapper attendeeMapper;
    private final UserService userService;
    private final MeetingContextMapper meetingContextMapper;
    private final EmailService emailService;

    public MeetingDto createNewMeeting(UUID userId, NewMeetingDto newMeetingDto) {
        Meeting meeting = meetingMapper.toDomain(newMeetingDto);

        List<Attendee> attendees = new ArrayList<>(newMeetingDto.attendees().stream().map(
                a -> {
                    User user = userService.get(a.userId());
                    return new Attendee(a.userId(), user.getRole());
                }).toList());

        User currentUser = userService.get(userId);
        attendees.add(new Attendee(userId, currentUser.getRole()));
        meeting.validateMinimumRoles(attendees);
        meeting.assignAttendees(attendees);
        meetingRepository.save(meeting);

        // send emails 1 hour before meeting
        attendees.forEach(a -> emailService.sendEmailReminder(a.getUserId(), meeting));

        return meetingMapper.toResponseDto(meeting);
    }

    public List<MeetingDto> getAllMeetings() {
        return meetingRepository.findAll().stream().map(meetingMapper::toResponseDto).toList();
    }

    public List<MeetingDto> getAllMeetingsByUser(UUID userId,
                                                 Integer quarter,
                                                 Integer year,
                                                 List<UUID> personIds) {
        return meetingRepository
                .findAllByAttendeesUserId(userId)
                .stream()

                .filter(meeting -> {
                    if (year == null) {
                        return true;
                    }

                    return meeting
                            .getStartDateTime()
                            .getYear() == year;
                })

                .filter(meeting -> {
                    if (quarter == null) {
                        return true;
                    }

                    int month = meeting
                            .getStartDateTime()
                            .getMonthValue();

                    int meetingQuarter = ((month - 1) / 3) + 1;

                    return meetingQuarter == quarter;
                })

                .filter(meeting -> {
                    if (personIds == null || personIds.isEmpty()) {
                        return true;
                    }

                    return meeting
                            .getAttendees()
                            .stream()
                            .anyMatch(attendee ->
                                    personIds.contains(attendee.getUserId())
                            );
                })

                .map(meetingMapper::toResponseDto)

                .toList();
    }

    public List<AttendeeDto> getMeetingAttendees(UUID userId, UUID meetingId) {
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

    public MeetingContextDto getNextAndLastMeeting(UUID userId) {
        MeetingDto nextMeeting = meetingRepository
                .findFirstByAttendees_UserIdAndStartDateTimeAfterOrderByStartDateTimeAsc(
                        userId,
                        LocalDateTime.now()
                )
                .map(meetingMapper::toResponseDto)
                .orElse(null);

        MeetingDto latestMeeting = meetingRepository
                .findFirstByAttendees_UserIdAndStartDateTimeBeforeOrderByStartDateTimeDesc(
                        userId,
                        LocalDateTime.now()
                )
                .map(meetingMapper::toResponseDto)
                .orElse(null);
        return meetingContextMapper.toContextDto(nextMeeting, latestMeeting);
    }

    public void save(Meeting meeting) {
        meetingRepository.save(meeting);
    }
}
