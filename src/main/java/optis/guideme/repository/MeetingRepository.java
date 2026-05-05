package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface MeetingRepository extends JpaRepository<@NonNull Meeting, @NonNull UUID> {
    Optional<Meeting> findFirstByStartDateTimeAfterOrderByStartDateTimeAsc(LocalDateTime now);;

    List<Meeting> findAllByAttendees_User_UserId(UUID userId);

    @Query("""
            SELECT a FROM Attendee a
            WHERE a.meeting.meetingId = :meetingId
            AND a.user.userId <> :userId
            """)
    List<Attendee> findOtherAttendees(UUID meetingId, UUID userId);

    Meeting findMeetingByMeetingId(UUID meetingId);
}
