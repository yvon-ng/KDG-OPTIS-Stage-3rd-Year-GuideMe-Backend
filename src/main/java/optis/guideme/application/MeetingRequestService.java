package optis.guideme.application;

import optis.guideme.api.dto.MeetingRequestDto;
import optis.guideme.domain.meetingRequest.*;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;
import optis.guideme.domain.user.UserId;
import optis.guideme.persistence.DbMeetingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class MeetingRequestService {
    private final DbMeetingRequestRepository dbMeetingRequestRepository;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;
    private final UserService userService;


    public MeetingRequestService(DbMeetingRequestRepository dbMeetingRequestRepository, DefaultReferenceCodeGenerator defaultReferenceCodeGenerator, UserService userService) {
        this.dbMeetingRequestRepository = dbMeetingRequestRepository;
        this.defaultReferenceCodeGenerator = defaultReferenceCodeGenerator;
        this.userService = userService;
    }

    public MeetingRequest createMeetingRequest(MeetingRequestDto meetingRequestDto) {
        UserId requestedBy = UserId.of(meetingRequestDto.requestedBy());

        List<UserId> requestFor = meetingRequestDto.requestFor().stream()
                .map(UserId::of)
                .toList();

        //check if request is allowed (at leasty one manager)
        User requester = userService.findById(requestedBy);
        List<User> requestedUsers = meetingRequestDto.requestFor().stream()
                .map(UserId::of)
                .map(userService::findById)
                .toList();

        //TODO: maybe this with a db search instead of loading full user objects in service ...
        boolean isAllowed =
                requester.getRole() == Role.MANAGER || requestedUsers.stream().anyMatch(user -> user.getRole().equals(Role.MANAGER));

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

        //getrequested users names:
        //todo find solution for people with the sam e names
        List<String> names = requestedUsers.stream()
                .map(User::getFirstName)
                .toList();

        while (meetingRequest.getReferenceCode() == null){
                String refCode = defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest, names);
                //TODO hardcoded for now: check if ref code is unique in db
                boolean isUnique = true;
                if (isUnique){
                    meetingRequest.assignReferenceCode(refCode);
                }
        }
        return dbMeetingRequestRepository.save(meetingRequest);
    }
}

