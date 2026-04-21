package optis.guideme.domain.user;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

public record Client(String clientName, LocalDate startDateAtClient) {
}
