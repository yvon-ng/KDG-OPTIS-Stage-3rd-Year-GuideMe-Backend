package optis.guideme.persistence;

import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meeting.MeetingRepository;
import optis.guideme.persistence.meeting.JpaMeetingEntity;
import optis.guideme.persistence.meeting.JpaMeetingRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DbMeetingRepository implements MeetingRepository {
    private final JpaMeetingRepository jpaMeetingRepository;

    public DbMeetingRepository(JpaMeetingRepository jpaMeetingRepository) {
        this.jpaMeetingRepository = jpaMeetingRepository;
    }

    @Override
    public Meeting save(Meeting meeting) {
        JpaMeetingEntity entity = JpaMeetingEntity.fromDomain(meeting);
        JpaMeetingEntity saved = jpaMeetingRepository.save(entity);
        return JpaMeetingEntity.toDomain(saved);

    }
}
