package optis.guideme.api.mapper.request;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewMeetingRequestMapper {

    public MeetingRequest toDomain(NewMeetingRequestDto newMeetingRequestDto) {
        return new MeetingRequest(
                newMeetingRequestDto.endDateTime(),
                newMeetingRequestDto.name(),
                newMeetingRequestDto.recurrence(),
                newMeetingRequestDto.requestFor(),
                newMeetingRequestDto.startDateTime()
        );
    }
//    public NewMeetingRequestDto toDto(MeetingRequest  meetingRequest) {
//        return new NewMeetingRequestDto(
//                meetingRequest.getName(),
//                meetingRequest.getStartDateTime(),
//                meetingRequest.getEndDateTime(),
//                meetingRequest.getRecurrenceIntervalInMonths(),
//                meetingRequest.getRequestFor()
//                        .stream()
//                        .map(User::getUserId)
//                        .toList(),
//                meetingRequest.getPreviousRequestId(),
//                meetingRequest.getRequestedBy().getUserId()
//        );
//    }

    public MeetingRequestDto toResponseDto(MeetingRequest meetingRequest) {
        return new MeetingRequestDto(
                meetingRequest.getName(),
                meetingRequest.getReferenceCode(),
                meetingRequest.getStartDateTime(),
                meetingRequest.getEndDateTime(),
                meetingRequest.getRecurrencePerYear(),
                meetingRequest.getRequestFor(),
                meetingRequest.getPreviousRequestId(),
                meetingRequest.getPreviousRequestId()
        );
    }
}
