package optis.guideme.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientScore {
    private String clientName;
    @Enumerated(EnumType.STRING)
    private Score score;
    private LocalDate date;
}
