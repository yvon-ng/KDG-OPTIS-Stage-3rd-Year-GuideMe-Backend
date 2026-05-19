package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.response.NotificationDto;
import optis.guideme.api.mapper.NotificationMapper;
import optis.guideme.domain.Notification;
import optis.guideme.domain.NotificationType;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationMapper notificationMapper;
    private final NotificationTemplateService templateService;

    public void sendAcceptedMeetingRequestNotification(UUID userId, Meeting meeting) {
        String title = templateService.render("meeting.request.accepted.title");
        String description = templateService.render("meeting.request.accepted.description", meeting.getDisplayName());
        Notification notification = new Notification(userId, title, description, NotificationType.MEETING_ACCEPTED);
        notificationRepository.save(notification); // save first so id gets generated
        NotificationDto dto = notificationMapper.toResponseDto(notification);
        messagingTemplate.convertAndSendToUser(
                userId.toString(),
                "/queue/notifications",
                dto
        );
    }

    public void sendDeclinedMeetingRequestNotification(UUID requestedBy, MeetingRequest meetingRequest) {
        String title = templateService.render("meeting.request.declined.title");
        String description = templateService.render("meeting.request.declined.description", meetingRequest.getDisplayName());
        Notification notification = new Notification(requestedBy, title, description, NotificationType.MEETING_DECLINED);
        notificationRepository.save(notification); // save first so id gets generated
        NotificationDto dto = notificationMapper.toResponseDto(notification);
        messagingTemplate.convertAndSendToUser(
                requestedBy.toString(),
                "/queue/notifications",
                dto
        );
    }
}
