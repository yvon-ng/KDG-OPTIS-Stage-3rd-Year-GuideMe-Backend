package optis.guideme.api.mapper;

import optis.guideme.api.dto.request.NewGoalDto;
import optis.guideme.api.dto.response.GoalDto;
import optis.guideme.domain.Goal;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class GoalMapper {
    public Goal toDomain(NewGoalDto newGoalDto) {
        return new Goal(
                newGoalDto.title(),
                newGoalDto.description(),
                newGoalDto.dueDate().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }

    public GoalDto toResponseDto(Goal goal) {
        return new GoalDto(
                goal.getGoalId(),
                goal.getTitle(),
                goal.getDescription(),
                goal.getCreationDate(),
                goal.getDueDate(),
                goal.getAchievementDate()
        );
    }
}
