package optis.guideme.api.mapper;

import optis.guideme.api.dto.request.MeetingRequestDto;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.user.User;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingRequestMapper {
    private final UserRepository userRepository;

    public MeetingRequestMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public MeetingRequest toDomain(MeetingRequestDto meetingRequestDto) {
        User requestedBy = userRepository.findById(meetingRequestDto.requestedBy()).orElseThrow(() -> new NotFoundException("User not found with id " + meetingRequestDto.requestedBy()));
        List<User> requestFor = userRepository.findAllById(meetingRequestDto.requestFor());
        return new MeetingRequest(
                meetingRequestDto.endDateTime(),
                false,
                meetingRequestDto.name(),
                meetingRequestDto.previousRequestId(),
                meetingRequestDto.recurrence(),
                null,
                requestedBy,
                requestFor,
                meetingRequestDto.startDateTime(),
                null
        );
    }
    public MeetingRequestDto toDto(MeetingRequest  meetingRequest) {
        return new MeetingRequestDto(
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
}
