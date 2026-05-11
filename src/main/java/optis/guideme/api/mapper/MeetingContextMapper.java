package optis.guideme.api.mapper;

import optis.guideme.api.dto.response.MeetingContextDto;
import optis.guideme.api.dto.response.MeetingDto;
import org.springframework.stereotype.Component;

@Component
public class MeetingContextMapper {
    public MeetingContextDto toContextDto(MeetingDto nextMeetingDto, MeetingDto latestMeetingDto) {
        return new MeetingContextDto(
                nextMeetingDto,
                latestMeetingDto
                );
    }

}
