package optis.guideme.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.UpdateGoalDto;
import optis.guideme.api.dto.request.NewGoalDto;
import optis.guideme.api.dto.response.GoalDto;
import optis.guideme.api.mapper.CurrentUserProvider;
import optis.guideme.application.GoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/goals")
public class GoalController {
    private final GoalService goalService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping
    public List<GoalDto> getMyGoals(){
        return goalService.getAllGoalsByUser(currentUserProvider.getUserId());
    }

    @PostMapping
    public GoalDto createNewGoal(@RequestBody @Valid NewGoalDto newGoalDto){
        return goalService.createNewGoal(currentUserProvider.getUserId(), newGoalDto);
    }

    @PutMapping("{goalId}/complete")
    public GoalDto completeGoal(@PathVariable UUID goalId){
        return goalService.completeGoal(currentUserProvider.getUserId(), goalId);
    }

    @PutMapping("/{goalId}")
    public GoalDto updateGoal(@PathVariable UUID goalId, @RequestBody @Valid UpdateGoalDto updateGoalDto){
        return goalService.updateGoal(currentUserProvider.getUserId(), goalId, updateGoalDto);
    }

    @GetMapping("completed")
    public List<GoalDto> getMyCompletedGoals(){
        return goalService.getAllCompletedGoalsByUser(currentUserProvider.getUserId());
    }
}
