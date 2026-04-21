package optis.guideme.domain.user.client;

import java.time.LocalDate;

public record ClientRating (Client client, Rating rating, LocalDate date){ }
