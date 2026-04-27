package optis.guideme.repository;

import lombok.NonNull;
import optis.guideme.domain.meeting.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttendeeRepository extends JpaRepository<@NonNull Attendee, @NonNull UUID> {

}
