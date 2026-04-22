package optis.guideme.domain.meetingRequest;

import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRequestRepository {
    boolean existsByReferenceCode(String code);
    MeetingRequest save(MeetingRequest meetingRequest);
}
