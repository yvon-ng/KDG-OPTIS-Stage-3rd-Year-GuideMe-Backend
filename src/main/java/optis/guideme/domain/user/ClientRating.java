package optis.guideme.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;


@Entity
public class ClientRating {
    @Id
    @GeneratedValue
    private UUID clientId;
    private String currentClient;
    private Rating rating;
    private LocalDate date;
}
