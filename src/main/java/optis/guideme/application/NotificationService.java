package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.NotificationDto;
import optis.guideme.api.mapper.NotificationMapper;
import optis.guideme.domain.Notification;
import optis.guideme.domain.NotificationType;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.user.User;
import optis.guideme.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationMapper notificationMapper;
    private final NotificationTemplateService templateService;

    //TODO check if correct notification constructor
    public void sendAcceptedMeetingRequestNotification(UUID userId, Meeting meeting) {
        String title = templateService.render("meeting.request.accepted.title");
        String description = templateService.render("meeting.request.accepted.description", meeting.getDisplayName());
        Notification notification = new Notification(userId, title, description, NotificationType.MEETING_ACCEPTED);
        notificationRepository.save(notification); // save first so id gets generated
        NotificationDto notificationDto = notificationMapper.toResponseDto(notification);
        messagingTemplate.convertAndSendToUser(
                userId.toString(),
                "/queue/notifications",
                notificationDto
        );
    }

    //TODO check if correct notification constructor
    public void sendDeclinedMeetingRequestNotification(UUID requestedBy, MeetingRequest meetingRequest) {
        String title = templateService.render("meeting.request.declined.title");
        String description = templateService.render("meeting.request.declined.description", meetingRequest.getDisplayName());
        Notification notification = new Notification(requestedBy, title, description, NotificationType.MEETING_DECLINED);
        notificationRepository.save(notification); // save first so id gets generated
        NotificationDto notificationDto = notificationMapper.toResponseDto(notification);
        messagingTemplate.convertAndSendToUser(
                requestedBy.toString(),
                "/queue/notifications",
                notificationDto
        );
    }

    //TODO check if correct notification constructor
    public void sendRescheduledMeetingRequestNotification(UUID requestedBy, MeetingRequest meetingRequest) {
        String title = templateService.render("meeting.request.rescheduled.title");
        String description = templateService.render("meeting.request.rescheduled.description", meetingRequest.getDisplayName());
        Notification notification = new Notification(requestedBy, title, description, NotificationType.MEETING_RESCHEDULED);
        notificationRepository.save(notification); // save first so id gets generated
        NotificationDto notificationDto = notificationMapper.toResponseDto(notification);
        messagingTemplate.convertAndSendToUser(
                requestedBy.toString(),
                "/queue/notifications",
                notificationDto
        );
    }

    public void sendFillInProfileNotification(UUID requestedBy) {
        String title = templateService.render("profile.fill.in.title");
        String description = templateService.render("profile.fill.in.description");
        Notification notification = new Notification(requestedBy, title, description,NotificationType.FILL_IN_PROFILE);
        notificationRepository.save(notification); // save first so id gets generated
        NotificationDto notificationDto = notificationMapper.toResponseDto(notification);
        messagingTemplate.convertAndSendToUser(
                requestedBy.toString(),
                "/queue/notifications",
                notificationDto
        );
    }

    public List<NotificationDto> getSentNotifications(UUID userId) {
        return notificationRepository.findAllByReceiverAndSentAtIsNotNull(userId).map(notificationMapper::toResponseDto).toList();
    }

    public void sendLowClientScoreNotification(List<User> managers, String consultantFirstName, String consultantLastName, int score) {
        String title = templateService.render("client.score.low.title", consultantFirstName);
        String description = templateService.render("client.score.low.description", consultantFirstName, consultantLastName, String.valueOf(score));
        for (User manager : managers) {
            Notification notification = new Notification(manager.getUserId(), title, description, NotificationType.LOW_CLIENT_SCORE);
            notificationRepository.save(notification);
            NotificationDto notificationDto = notificationMapper.toResponseDto(notification);
            messagingTemplate.convertAndSendToUser(
                    manager.getUserId().toString(),
                    "/queue/notifications",
                    notificationDto
            );
        }
    }

}
