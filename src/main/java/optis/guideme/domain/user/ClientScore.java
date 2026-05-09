package optis.guideme.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class ClientScore {
    private String clientName;
    @Enumerated(EnumType.STRING)
    private Score score;
    private LocalDate date;
}
