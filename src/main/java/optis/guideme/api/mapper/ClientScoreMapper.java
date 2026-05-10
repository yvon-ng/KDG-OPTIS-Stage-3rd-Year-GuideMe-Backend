package optis.guideme.api.mapper;

import optis.guideme.api.dto.request.NewClientScoreDto;
import optis.guideme.api.dto.response.ClientScoreDto;
import optis.guideme.domain.user.ClientScore;
import optis.guideme.domain.user.Score;
import org.springframework.stereotype.Component;

@Component
public class ClientScoreMapper {
    public Score newClientScoreToDomain(NewClientScoreDto newClientScoreDto) {
        return Score.fromValue(newClientScoreDto.score());
    }

    public ClientScoreDto toResponseDto(ClientScore score) {
        return new ClientScoreDto(
                score.getClientName(),
                score.getScore().getValue(),
                score.getDate()
        );
    }
}
