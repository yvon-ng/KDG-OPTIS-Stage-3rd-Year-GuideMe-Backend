package optis.guideme.domain.user;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ClientRating {
    private String currentClient;
    private Rating rating;
    private LocalDate date;
}
