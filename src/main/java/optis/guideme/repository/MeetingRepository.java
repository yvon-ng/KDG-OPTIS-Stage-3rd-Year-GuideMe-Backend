package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public interface MeetingRepository extends JpaRepository<@NonNull Meeting, @NonNull UUID> {
    List<Meeting> findAllByAttendees_User_UserId(UUID userId);
//    Meeting save(Meeting meeting);
}
