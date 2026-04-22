package optis.guideme.application;

import optis.guideme.api.dto.MeetingRequestDto;
import optis.guideme.domain.meetingRequest.*;
import optis.guideme.domain.user.Manager;
import optis.guideme.domain.user.User;
import optis.guideme.domain.user.UserId;
import optis.guideme.persistence.DbMeetingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MeetingRequestService {
    private final DbMeetingRequestRepository dbMeetingRequestRepository;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;

    public MeetingRequestService(DbMeetingRequestRepository dbMeetingRequestRepository, DefaultReferenceCodeGenerator defaultReferenceCodeGenerator) {
        this.dbMeetingRequestRepository = dbMeetingRequestRepository;
        this.defaultReferenceCodeGenerator = defaultReferenceCodeGenerator;
    }

    public MeetingRequest createMeetingRequest(MeetingRequestDto meetingRequestDto) {
        Manager manager = new Manager(UserId.of(UUID.randomUUID()),"firstName", "lastName", "email");
        //TODO: gaat later een check zijn ma voor nu ff hardcoded
        User requestedBy = manager;

        List<User> requestFor = meetingRequestDto.requestFor().stream()
                .map(uuid -> (User) new Manager(UserId.of(UUID.randomUUID()),"firstName", "lastName", "email"))
                .toList();

        boolean isAllowed = requestedBy instanceof Manager ||
                requestFor.stream().anyMatch(user -> user instanceof Manager);

        MeetingRequest meetingRequest = new MeetingRequest(
                meetingRequestDto.endDateTime(),
                isAllowed,
                new MeetingRequestId(UUID.randomUUID()),
                meetingRequestDto.name(),
                meetingRequestDto.previousRequestId(),
                meetingRequestDto.recurrence(),
                null,
                requestedBy,
                requestFor,
                meetingRequestDto.startDateTime(),
                Status.PENDING
        );

        while (meetingRequest.getReferenceCode() == null){
                String refCode = defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest);
                //TODO hardcoded for now: check if ref code is unique in db
                boolean isUnique = true;
                if (isUnique){
                    meetingRequest.assignReferenceCode(refCode);
                }
        }
        return dbMeetingRequestRepository.save(meetingRequest);
    }
}

















