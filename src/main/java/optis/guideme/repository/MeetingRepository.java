package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeetingRepository extends JpaRepository<@NonNull Meeting, @NonNull UUID> {
//    Meeting save(Meeting meeting);
}
