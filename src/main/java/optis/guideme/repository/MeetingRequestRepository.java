package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeetingRequestRepository extends JpaRepository<@NonNull MeetingRequest, @NonNull UUID> {
    boolean existsByReferenceCode(String code);
    MeetingRequest save(MeetingRequest meetingRequest);
}
