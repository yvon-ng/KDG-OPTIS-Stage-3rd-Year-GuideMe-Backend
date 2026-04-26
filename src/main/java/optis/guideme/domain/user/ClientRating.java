package optis.guideme.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class ClientRating {
    private String clientName;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private LocalDate date;
}
