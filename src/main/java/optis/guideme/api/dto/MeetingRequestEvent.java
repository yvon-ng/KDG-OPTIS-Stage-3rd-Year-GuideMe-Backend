package optis.guideme.api.dto;

import optis.guideme.api.dto.response.MeetingRequestDto;

public record MeetingRequestEvent(MeetingRequestEventType type, MeetingRequestDto meetingRequestDto) {

}