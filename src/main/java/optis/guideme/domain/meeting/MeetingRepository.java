package optis.guideme.domain.meeting;

import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository {
    void save(Meeting meeting);
}
