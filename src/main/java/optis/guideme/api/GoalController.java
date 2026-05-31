package optis.guideme.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateGoalDto;
import optis.guideme.api.dto.request.NewGoalDto;
import optis.guideme.api.dto.response.GoalDto;
import optis.guideme.configs.CurrentUserProvider;
import optis.guideme.application.GoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/goals")
public class GoalController {
    private final GoalService goalService;

    @GetMapping("/me")
    public List<GoalDto> getMyUncompletedGoals() {
        return goalService.getAllUncompletedGoalsByUser(CurrentUserProvider.getUserId());
    }

    @GetMapping
    public List<GoalDto> getUserUncompletedGoals(@RequestParam UUID userId) {
        if (userId == null) {
            return getMyUncompletedGoals(); // fallback for "current user" or global
        }

        return goalService.getAllUncompletedGoalsByUser(userId);
//        return goalService.getAllUncompletedGoalsByUser(userId);
    }

    @PostMapping
    public GoalDto createNewGoal(@RequestBody @Valid NewGoalDto newGoalDto) {
        return goalService.createNewGoal(CurrentUserProvider.getUserId(), newGoalDto);
    }

    @PutMapping("{goalId}/complete")
    public GoalDto completeGoal(@PathVariable UUID goalId) {
        return goalService.completeGoal(CurrentUserProvider.getUserId(), goalId);
    }

    @PutMapping("{goalId}")
    public GoalDto updateGoal(@PathVariable UUID goalId, @RequestBody @Valid UpdateGoalDto updateGoalDto) {
        return goalService.updateGoal(CurrentUserProvider.getUserId(), goalId, updateGoalDto);
    }

    @GetMapping("completed/me")
    public List<GoalDto> getMyCompletedGoals() {
        return goalService.getAllCompletedGoalsByUser(CurrentUserProvider.getUserId());
    }

    @GetMapping("completed")
    public List<GoalDto> getUserCompletedGoals(@RequestParam UUID userId) {
        return goalService.getAllCompletedGoalsByUser(userId);
    }
}
