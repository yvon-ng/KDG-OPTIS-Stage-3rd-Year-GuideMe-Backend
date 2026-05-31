package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.MeetingRequestEvent;
import optis.guideme.api.dto.MeetingRequestEventType;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final MeetingRequestMapper meetingRequestMapper;
    private final MeetingMapper meetingMapper;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final UserService userService;
    private final MeetingService meetingService;
    private final EmailService emailService;
    private final ReminderService reminderService;
    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    private MeetingRequest getMeetingRequest(UUID meetingRequestId) {
        return meetingRequestRepository.findById(meetingRequestId).orElseThrow();
    }

    public MeetingRequestDto createMeetingRequest(UUID userId, NewMeetingRequestDto newMeetingRequestDto) {
        List<User> requestFor = userService.getUsersByIds(newMeetingRequestDto.requestFor());

        if (newMeetingRequestDto.previousRequestId() != null) return rescheduleRequest(userId, newMeetingRequestDto);

        User requestedBy = userService.getUser(userId);

        MeetingRequest meetingRequest = meetingRequestMapper.toDomain(newMeetingRequestDto);
        meetingRequest.assignRequestedBy(userId);
        meetingRequest.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest, requestedBy, requestFor));

        // save first so id gets generated, then via websocket update incoming requests of users
        meetingRequestRepository.save(meetingRequest);
        MeetingRequestDto meetingRequestDto = meetingRequestMapper.toResponseDto(meetingRequest);

        // live update the incoming requests via websocket for every requestFor
        MeetingRequestEvent createdEvent = new MeetingRequestEvent(MeetingRequestEventType.CREATED, meetingRequestDto);
        requestFor.forEach(user -> sendWebsocketMessage(user.getUserId(), "/queue/incoming-meeting-requests", createdEvent));

        // live update the outgoing requests via websocket for the requestedBy
        sendWebsocketMessage(requestedBy.getUserId(), "/queue/outgoing-meeting-requests", createdEvent);

        return meetingRequestDto;
    }

    private void sendWebsocketMessage(UUID userId, String destinationQueue, MeetingRequestEvent meetingRequestEvent){
        messagingTemplate.convertAndSendToUser(
                userId.toString(),
                destinationQueue,
                meetingRequestEvent
        );
    }

    private MeetingRequestDto rescheduleRequest(UUID userId, NewMeetingRequestDto newMeetingRequestDto){
        MeetingRequest previous = getMeetingRequest(newMeetingRequestDto.previousRequestId());
        LocalDateTime newStart = LocalDateTime.ofInstant(newMeetingRequestDto.startDateTime(), ZoneId.systemDefault());
        LocalDateTime newEnd = LocalDateTime.ofInstant(newMeetingRequestDto.endDateTime(), ZoneId.systemDefault());
        MeetingRequest rescheduled = previous.reschedule(newStart, newEnd, newMeetingRequestDto.recurrence(), userId, newMeetingRequestDto.requestFor());

        meetingRequestRepository.save(rescheduled);
        MeetingRequestDto meetingRequestDto = meetingRequestMapper.toResponseDto(rescheduled);
        List<User> users = userService.getUsersByIds(rescheduled.getRequestFor());

        // REMOVE old
        MeetingRequestEvent removed = new MeetingRequestEvent(MeetingRequestEventType.REMOVED, meetingRequestMapper.toResponseDto(previous));

        // CREATE new
        MeetingRequestEvent created = new MeetingRequestEvent(MeetingRequestEventType.CREATED, meetingRequestDto);

        // incoming
        users.forEach(user -> {
            sendWebsocketMessage(user.getUserId(), "/queue/incoming-meeting-requests", removed);
            sendWebsocketMessage(user.getUserId(), "/queue/incoming-meeting-requests", created);
        });

        // outgoing
        sendWebsocketMessage(previous.getRequestedBy(), "/queue/outgoing-meeting-requests", removed);
        sendWebsocketMessage(rescheduled.getRequestedBy(), "/queue/outgoing-meeting-requests", created);

        notificationService.sendRescheduledMeetingRequestNotification(previous.getRequestedBy(), previous);
        return meetingRequestDto;
    }

    public List<MeetingRequestDto> getAllMeetingRequests() {
        return meetingRequestRepository.findAll().stream().map(meetingRequestMapper::toResponseDto).toList();
    }

    public List<MeetingRequestDto> getIncomingMeetingRequests(UUID userId) {
        return meetingRequestRepository.findIncomingMeetingRequests(userId).map(meetingRequestMapper::toResponseDto).toList();
    }

    public List<MeetingRequestDto> getOutgoingMeetingRequests(UUID userId) {
        return meetingRequestRepository.findOutgoingMeetingRequests(userId).map(meetingRequestMapper::toResponseDto).toList();
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

        MeetingRequestEvent removed = new MeetingRequestEvent(MeetingRequestEventType.REMOVED, meetingRequestMapper.toResponseDto(meetingRequest));
        sendWebsocketMessage(meetingRequest.getRequestedBy(), "/queue/outgoing-meeting-requests", removed);

        attendees.forEach(user -> sendWebsocketMessage(user.getUserId(), "/queue/incoming-meeting-requests", removed));

        return meetingMapper.toResponseDto(meeting);
    }

    public void declineRequest(UUID meetingRequestId) {
        MeetingRequest meetingRequest = getMeetingRequest(meetingRequestId);
        meetingRequest.decline();
        List<Attendee> attendees = mapParticipantsToAttendees(meetingRequest);

        // send decliend notification and declined emails immediately
        notificationService.sendDeclinedMeetingRequestNotification(meetingRequest.getRequestedBy(), meetingRequest);
        attendees.forEach(a -> emailService.sendDeclinedEmail(a.getUserId(), meetingRequest));

        MeetingRequestEvent removed = new MeetingRequestEvent(MeetingRequestEventType.REMOVED, meetingRequestMapper.toResponseDto(meetingRequest));
        sendWebsocketMessage(meetingRequest.getRequestedBy(), "/queue/outgoing-meeting-requests", removed);

        attendees.forEach(user -> sendWebsocketMessage(user.getUserId(), "/queue/incoming-meeting-requests", removed));

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


