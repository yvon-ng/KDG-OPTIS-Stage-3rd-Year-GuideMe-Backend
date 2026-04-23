package optis.guideme.domain.meeting;

import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository {
    Meeting save(Meeting meeting);
}
