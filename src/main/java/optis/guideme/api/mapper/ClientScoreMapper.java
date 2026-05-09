package optis.guideme.api.mapper;

import optis.guideme.api.dto.response.ClientScoreDto;
import optis.guideme.domain.user.ClientScore;
import org.springframework.stereotype.Component;

@Component
public class ClientScoreMapper {
    public ClientScoreDto toDto(ClientScore score) {
        return new ClientScoreDto(
                score.getClientName(),
                score.getScore().getValue(),
                score.getDate()
        );
    }
}
