//package optis.guideme.repository;
//
//import lombok.NonNull;
//import optis.guideme.domain.meeting.Attendee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//public interface AttendeeRepository extends JpaRepository<@NonNull Attendee, @NonNull UUID> {
//    boolean existsByMeeting_MeetingIdAndUser_UserId(UUID meetingId, UUID userId);
//
//    @Query("""
//        SELECT a FROM Attendee a
//        WHERE a.meeting.meetingId = :meetingId
//          AND a.user.userId <> :userId
//    """)
//    List<Attendee> findOtherAttendees(
//            @Param("meetingId") UUID meetingId,
//            @Param("userId") UUID userId
//    );
//}
