package optis.guideme.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Client {
    private final String clientName;
    private final LocalDate startDateAtClient;
}
