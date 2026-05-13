package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface MeetingRepository extends JpaRepository<@NonNull Meeting, @NonNull UUID> {
    //after + asc -> nearest future method
    Optional<Meeting> getFirstByAttendees_UserIdAndStartDateTimeAfterOrderByStartDateTimeAsc(UUID userId, LocalDateTime now);
    //before + asc -> latest meeting
    Optional<Meeting> getFirstByAttendees_UserIdAndStartDateTimeBeforeOrderByStartDateTimeDesc(UUID userId, LocalDateTime now);

    Stream<Meeting> getAllByAttendeesUserId(UUID userId);

    Meeting get(UUID meetingId);
}
