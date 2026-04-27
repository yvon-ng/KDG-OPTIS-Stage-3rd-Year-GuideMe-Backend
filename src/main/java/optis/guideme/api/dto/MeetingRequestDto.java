package optis.guideme.api.dto;

import lombok.NonNull;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.meetingRequest.MeetingRequestId;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//TODO: for now UUID for requestedBy directly, later via security: get currentUser.id() or something
public record MeetingRequestDto(@NonNull String name, LocalDateTime startDateTime, @Nullable LocalDateTime endDateTime,int recurrence, List<UUID> requestFor, @Nullable MeetingRequestId previousRequestId, UUID requestedBy) {
    public static MeetingRequestDto fromDomain(MeetingRequest meetingRequest) {
        return new MeetingRequestDto(
                meetingRequest.getName(),
                meetingRequest.getStartDateTime(),
                meetingRequest.getEndDateTime(),
                meetingRequest.getRecurrenceIntervalInMonths(),
                meetingRequest.getRequestFor()
                        .stream()
                        .map(userId -> userId.userId())
                        .toList(),
                meetingRequest.getPreviousRequestId(),
                meetingRequest.getRequestedBy().userId()
        );
    }
}
