package optis.guideme.api.mapper.request;

import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.meetingRequest.Status;
import optis.guideme.domain.user.User;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewMeetingRequestMapper {
    private final UserRepository userRepository;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;


    public NewMeetingRequestMapper(UserRepository userRepository, DefaultReferenceCodeGenerator defaultReferenceCodeGenerator) {
        this.userRepository = userRepository;
        this.defaultReferenceCodeGenerator = defaultReferenceCodeGenerator;
    }
    public MeetingRequest toDomain(NewMeetingRequestDto newMeetingRequestDto) {
        User requestedBy = userRepository.findById(newMeetingRequestDto.requestedBy()).orElseThrow(() -> new NotFoundException("User not found with id " + newMeetingRequestDto.requestedBy()));
        List<User> requestFor = userRepository.findAllById(newMeetingRequestDto.requestFor());
        MeetingRequest meetingRequest = new MeetingRequest(
                newMeetingRequestDto.endDateTime(),
                false,
                newMeetingRequestDto.name(),
                newMeetingRequestDto.previousRequestId(),
                newMeetingRequestDto.recurrence(),
                requestedBy,
                requestFor,
                newMeetingRequestDto.startDateTime(),
                Status.PENDING
        );
        meetingRequest.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCode(meetingRequest));
        return meetingRequest;
    }
    public NewMeetingRequestDto toDto(MeetingRequest  meetingRequest) {
        return new NewMeetingRequestDto(
                meetingRequest.getName(),
                meetingRequest.getStartDateTime(),
                meetingRequest.getEndDateTime(),
                meetingRequest.getRecurrenceIntervalInMonths(),
                meetingRequest.getRequestFor()
                        .stream()
                        .map(User::getUserId)
                        .toList(),
                meetingRequest.getPreviousRequestId(),
                meetingRequest.getRequestedBy().getUserId()
        );
    }

    public MeetingRequestDto toResponseDto(MeetingRequest meetingRequest) {
        return new MeetingRequestDto(
                meetingRequest.getName(),
                meetingRequest.getReferenceCode(),
                meetingRequest.getStartDateTime(),
                meetingRequest.getEndDateTime(),
                meetingRequest.getRecurrenceIntervalInMonths(),
                meetingRequest.getRequestFor().stream().map(
                        User::getUserId
                ).toList(),
                meetingRequest.getPreviousRequestId(),
                meetingRequest.getPreviousRequestId()
        );
    }
}
