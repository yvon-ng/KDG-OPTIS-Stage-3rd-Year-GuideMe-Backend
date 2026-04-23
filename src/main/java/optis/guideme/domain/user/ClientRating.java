package optis.guideme.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class ClientRating {
    private String clientName;
    private Rating rating;
    private LocalDate date;
}
