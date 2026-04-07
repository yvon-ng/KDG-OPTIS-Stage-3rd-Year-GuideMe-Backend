package optis.guideme.domain.meetingRequest;

public interface MeetingRequestRepository {
    boolean existsByReferenceCode(String code);
}
