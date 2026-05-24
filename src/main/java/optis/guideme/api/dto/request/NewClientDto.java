package optis.guideme.api.dto.request;

import java.time.Instant;

public record NewClientDto(String clientName, Instant startDateAtClient) {
}
