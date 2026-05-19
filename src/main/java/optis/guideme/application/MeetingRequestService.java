package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.api.mapper.MeetingMapper;
import optis.guideme.api.mapper.MeetingRequestMapper;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meeting.Reminder;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class MeetingRequestService {
    private final MeetingRequestRepository meetingRequestRepository;
    private final MeetingRequestMapper mapper;
    private final MeetingMapper meetingMapper;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final UserService userService;
    private final MeetingService meetingService;
    private final EmailService emailService;
    private final ReminderService reminderService;
    private final NotificationService notificationService;

    private MeetingRequest getMeetingRequest(UUID meetingRequestId) {
        return meetingRequestRepository.findById(meetingRequestId).orElseThrow();
    }

    public MeetingRequestDto createMeetingRequest(UUID userId, NewMeetingRequestDto newMeetingRequestDto) {
        List<User> requestFor = userService.getUsersByIds(newMeetingRequestDto.requestFor());

        if (newMeetingRequestDto.previousRequestId() != null) {
            MeetingRequest previous = getMeetingRequest(newMeetingRequestDto.previousRequestId());
            LocalDateTime newStart = LocalDateTime.ofInstant(newMeetingRequestDto.startDateTime(), ZoneId.systemDefault());
            LocalDateTime newEnd = LocalDateTime.ofInstant(newMeetingRequestDto.endDateTime(), ZoneId.systemDefault());
            MeetingRequest rescheduled = previous.reschedule(newStart, newEnd, newMeetingRequestDto.recurrence(), userId, newMeetingRequestDto.requestFor());

            return mapper.toResponseDto(meetingRequestRepository.save(rescheduled));
        }
        User requestedBy = userService.getUser(userId);

        MeetingRequest meetingRequest = mapper.toDomain(newMeetingRequestDto);
        meetingRequest.assignRequestedBy(userId);
        meetingRequest.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest, requestedBy, requestFor));
        return mapper.toResponseDto(meetingRequestRepository.save(meetingRequest));
    }

    public List<MeetingRequestDto> getAllMeetingRequests() {
        return meetingRequestRepository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    public List<MeetingRequestDto> getIncomingMeetingRequests(UUID userId) {
        return meetingRequestRepository.findIncomingMeetingRequests(userId).map(mapper::toResponseDto).toList();
    }

    public List<MeetingRequestDto> getOutgoingMeetingRequests(UUID userId) {
        return meetingRequestRepository.findOutgoingMeetingRequests(userId).map(mapper::toResponseDto).toList();
    }

    public MeetingDto acceptRequest(UUID meetingRequestId) {
        MeetingRequest meetingRequest = getMeetingRequest(meetingRequestId);
        List<Attendee> attendees = mapParticipantsToAttendees(meetingRequest);
        Meeting meeting = meetingRequest.accept(attendees);
        meetingService.save(meeting); //save first so meetingId gets generated

        // send notification to requestedBy and accepted emails immediately
        notificationService.sendAcceptedMeetingRequestNotification(meetingRequest.getRequestedBy(), meeting);
        attendees.forEach(a -> emailService.sendAcceptedEmail(a.getUserId(), meeting));

        // send emails and reminders 1 hour before meeting
        attendees.forEach(a -> emailService.sendEmailReminder(a.getUserId(), meeting));
        List<Reminder> reminders = attendees.stream().map(a -> new Reminder(a.getUserId(), meeting)).toList();

        reminderService.saveAll(reminders);
        return meetingMapper.toResponseDto(meeting);
    }

    public void declineRequest(UUID meetingRequestId) {
        MeetingRequest meetingRequest = getMeetingRequest(meetingRequestId);
        meetingRequest.decline();
        List<Attendee> attendees = mapParticipantsToAttendees(meetingRequest);

        // send declined emails immediately TODO send notification
        attendees.forEach(a -> emailService.sendDeclinedEmail(a.getUserId(), meetingRequest));
    }

    public List<Attendee> mapParticipantsToAttendees(MeetingRequest meetingRequest){
        User requestedByUser = userService.getUser(meetingRequest.getRequestedBy());
        List<User> requestedForUsers = userService.getUsersByIds(meetingRequest.getRequestFor());
        List<Attendee> attendees = new ArrayList<>();
        attendees.add(new Attendee(meetingRequest.getRequestedBy(), requestedByUser.getRole()));
        attendees.addAll(requestedForUsers.stream().map(user -> new Attendee(user.getUserId(), user.getRole())).toList());
        return attendees;
    }
}


