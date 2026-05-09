package optis.guideme.api.mapper.request;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewAttendeeDto;
import optis.guideme.api.dto.response.AttendeeDto;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.user.Role;
import org.springframework.stereotype.Component;

@Component
public class NewAttendeeMapper {

    public Attendee toDomain(NewAttendeeDto newAttendeeDto, Role userRole){
        return new Attendee(
                newAttendeeDto.userId(),
                userRole
        );
    }

    public AttendeeDto toResponseDto(Attendee attendee) {
        return new AttendeeDto(
                attendee.getUserId()
        );
    }
}
