package optis.guideme.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    private LocalDateTime startDateTime;
    private List<Attendee> sharedUsers; // atleast two 1 manager 1 consultant !
}
