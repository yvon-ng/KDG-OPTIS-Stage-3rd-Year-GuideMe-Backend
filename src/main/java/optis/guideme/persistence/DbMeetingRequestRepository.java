package optis.guideme.persistence;

import optis.guideme.domain.meetingRequest.MeetingRequest;
import optis.guideme.domain.meetingRequest.MeetingRequestRepository;
import optis.guideme.persistence.meeting.JpaMeetingRequestEntity;
import optis.guideme.persistence.meeting.JpaMeetingRequestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DbMeetingRequestRepository implements MeetingRequestRepository {
    private final JpaMeetingRequestRepository repository;

    public DbMeetingRequestRepository(JpaMeetingRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByReferenceCode(String code) {
        return false;
    }

    @Override
    public MeetingRequest save(MeetingRequest meetingRequest) {
        JpaMeetingRequestEntity entity = JpaMeetingRequestEntity.fromDomain(meetingRequest);

        JpaMeetingRequestEntity saved = repository.save(entity);

        return JpaMeetingRequestEntity.toDomain(saved);
    }
}
