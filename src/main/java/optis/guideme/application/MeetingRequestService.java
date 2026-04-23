package optis.guideme.application;
import optis.guideme.api.dto.request.MeetingRequestDto;
import optis.guideme.api.mapper.MeetingRequestMapper;
import optis.guideme.repository.MeetingRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class MeetingRequestService {
    private final MeetingRequestRepository meetingRequestRepository;
    private final MeetingRequestMapper mapper;

    public MeetingRequestService(MeetingRequestRepository meetingRequestRepository, MeetingRequestMapper mapper) {
        this.meetingRequestRepository = meetingRequestRepository;
        this.mapper = mapper;
    }

    public MeetingRequestDto createMeetingRequest(MeetingRequestDto meetingRequestDto) {
       return mapper.toDto(meetingRequestRepository.save(mapper.toDomain(meetingRequestDto)));
    }


//    public MeetingRequest createMeetingRequest(MeetingRequestDto meetingRequestDto) {
//        UserId requestedBy = UserId.of(meetingRequestDto.requestedBy());
//
//        List<UserId> requestFor = meetingRequestDto.requestFor().stream()
//                .map(UserId::of)
//                .toList();
//
//        //check if request is allowed (at leasty one manager)
//        User requester = userService.findById(requestedBy);
//        List<User> requestedUsers = meetingRequestDto.requestFor().stream()
//                .map(UserId::of)
//                .map(userService::findById)
//                .toList();
//
//        boolean isAllowed =
//                requester instanceof Manager || requestedUsers.stream().anyMatch(user -> user instanceof Manager);
//
//        if (!isAllowed) {
//            throw new WrongInputException("Meeting Requests must include at least one manager!");
//        }
//
//        MeetingRequest meetingRequest = new MeetingRequest(
//                meetingRequestDto.endDateTime(),
//                true,
//                meetingRequestDto.name(),
//                meetingRequestDto.previousRequestId(),
//                meetingRequestDto.recurrence(),
//                null,
//                requestedBy,
//                requestFor,
//                meetingRequestDto.startDateTime(),
//                Status.PENDING
//        );
//
//        //getrequested users names:
//        //todo find solution for people with the sam e names
//        List<String> names = requestedUsers.stream()
//                .map(User::getFirstName)
//                .toList();
//
//        while (meetingRequest.getReferenceCode() == null){
//                String refCode = defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest, names);
//                //TODO hardcoded for now: check if ref code is unique in db
//                boolean isUnique = true;
//                if (isUnique){
//                    meetingRequest.assignReferenceCode(refCode);
//                }
//        }
//        return dbMeetingRequestRepository.save(meetingRequest);
//    }
}

