package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meetingRequest.MeetingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MeetingRequestRepository extends JpaRepository<@NonNull MeetingRequest, @NonNull UUID> {
//    boolean existsByReferenceCode(String code);
//    MeetingRequest save(MeetingRequest meetingRequest);

    @Query(
            """
            SELECT mr FROM MeetingRequest mr
            JOIN mr.requestFor rf
            WHERE mr.status = optis.guideme.domain.meetingRequest.Status.PENDING
            AND rf = :userId
            """
    )
    List<MeetingRequest> getIncomingMeetingRequests(UUID userId);

    @Query(
            """
            SELECT mr FROM MeetingRequest mr
            WHERE mr.status = optis.guideme.domain.meetingRequest.Status.PENDING
            AND mr.requestedBy = :userId
            """
    )
    List<MeetingRequest> getOutgoingMeetingRequests(UUID userId);
}
