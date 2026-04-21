package optis.guideme.domain.user.client;

import java.time.LocalDate;

public record ClientAssignment(Client client, LocalDate startDateAtClient) {}
