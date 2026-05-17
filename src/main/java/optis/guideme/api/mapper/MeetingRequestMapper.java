package optis.guideme.api.mapper;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingRequestDto;
import optis.guideme.api.dto.response.MeetingRequestDto;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Component
public class MeetingRequestMapper {
    public MeetingRequest toDomain(NewMeetingRequestDto newMeetingRequestDto) {
        LocalDateTime localStartDateTime = LocalDateTime.ofInstant(newMeetingRequestDto.startDateTime(), ZoneId.of("Europe/Brussels"));
        LocalDateTime localEndDateTime = LocalDateTime.ofInstant(newMeetingRequestDto.endDateTime(), ZoneId.of("Europe/Brussels"));
        return new MeetingRequest(
                localEndDateTime,
                newMeetingRequestDto.name(),
                newMeetingRequestDto.recurrence(),
                newMeetingRequestDto.requestFor(),
                localStartDateTime
        );
    }

    public MeetingRequestDto toResponseDto(MeetingRequest meetingRequest) {
        return new MeetingRequestDto(
                meetingRequest.getMeetingRequestId(),
                meetingRequest.getName(),
                meetingRequest.getReferenceCode(),
                meetingRequest.getStartDateTime(),
                meetingRequest.getEndDateTime(),
                meetingRequest.getRecurrencePerYear(),
                meetingRequest.getRequestFor(),
                meetingRequest.getPreviousRequestId(),
                meetingRequest.getRequestedBy()
        );
    }

}
