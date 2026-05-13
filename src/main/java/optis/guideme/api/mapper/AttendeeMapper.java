package optis.guideme.api.mapper;

import lombok.AllArgsConstructor;
import optis.guideme.api.dto.request.NewAttendeeDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.application.UserService;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AttendeeMapper {
    private final UserService userService;
    public Attendee toDomain(UUID userId) {
        User user = userService.get(userId);
        return new Attendee(userId, user.getRole());
    }

    public Attendee toDomain(NewAttendeeDto newAttendeeDto) {
        return new Attendee(newAttendeeDto.userId(), userService.get(newAttendeeDto.userId()).getRole());
    }

    public AttendeeDto toResponseDto(Attendee attendee) {
        return new AttendeeDto(attendee.getUserId());
    }
}
