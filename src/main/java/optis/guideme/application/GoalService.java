package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateGoalDto;
import optis.guideme.api.dto.request.NewGoalDto;
import optis.guideme.api.dto.response.GoalDto;
import optis.guideme.api.mapper.GoalMapper;
import optis.guideme.domain.Goal;
import optis.guideme.domain.exception.ForbiddenException;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.repository.GoalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    public List<GoalDto> getAllGoalsByUser(UUID userId) {
        return goalRepository.findAllByUserIdOrderByCreationDateDesc(userId).map(goalMapper::toResponseDto).toList();
    }

    public GoalDto createNewGoal(UUID userId, NewGoalDto newGoalDto) {
        Goal goal = goalMapper.toDomain(newGoalDto);
        goal.assignUser(userId);
        return goalMapper.toResponseDto(goalRepository.save(goal));
    }

    public GoalDto completeGoal(UUID userId, UUID goalId) {
        Goal goal = getGoal(userId, goalId);
        goal.complete();
        return goalMapper.toResponseDto(goalRepository.save(goal));
    }

    public GoalDto updateGoal(UUID userId, UUID goalId, UpdateGoalDto updateGoalDto) {
        Goal goal = getGoal(userId, goalId);
        goal.changeTitle(updateGoalDto.title());
        goal.changeDescription(updateGoalDto.description());
        goal.changeDueDate(updateGoalDto.dueDate());
        return goalMapper.toResponseDto(goalRepository.save(goal));
    }

    private Goal getGoal(UUID userId, UUID goalId) {
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new NotFoundException("Goal Not Found with Id: " + goalId));
        if (!userId.equals(goal.getUserId())) throw new ForbiddenException("You're not the owner of this goal: " + goalId);
        return goal;
    }

    public GoalDto get(UUID userId, UUID goalId) {
        return goalMapper.toResponseDto(getGoal(userId, goalId));
    }

    public List<GoalDto> getAllCompletedGoalsByUser(UUID userId) {
        return goalRepository.findAllByUserIdAndAchievementDateIsNotNullOrderByCreationDateDesc(userId).map(goalMapper::toResponseDto).toList();
    }
}
