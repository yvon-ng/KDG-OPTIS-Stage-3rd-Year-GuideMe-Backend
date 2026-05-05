package optis.guideme.application;

import lombok.RequiredArgsConstructor;
import optis.guideme.api.dto.request.NewMeetingDto;
import optis.guideme.api.dto.response.MeetingDto;
import optis.guideme.api.mapper.request.NewMeetingMapper;
import optis.guideme.domain.exception.NotFoundException;
import optis.guideme.domain.meeting.Attendee;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meetingRequest.DefaultReferenceCodeGenerator;
import optis.guideme.domain.user.User;
import optis.guideme.repository.MeetingRepository;
import optis.guideme.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final NewMeetingMapper mapper;
    private final DefaultReferenceCodeGenerator defaultReferenceCodeGenerator;

    public MeetingDto createNewMeeting(NewMeetingDto newMeetingDto) {
        Meeting meeting = mapper.toDomain(newMeetingDto);
        List<Attendee> attendees = newMeetingDto.attendees().stream()
                .map(a -> new Attendee(a.userId(), a.role()))
                .toList();

        meeting.validateMinimumRoles(attendees);
        meeting.assignReferenceCode(defaultReferenceCodeGenerator.generateReferenceCodeFromMeeting(meeting));
        return mapper.toResponseDto(meetingRepository.save(meeting));
    }

    public List<MeetingDto> getAllMeetings() {
        return meetingRepository.findAll().stream().map(
                mapper::toResponseDto
        ).toList();
    }

    public MeetingDto getNextMeeting() {
        return mapper.toResponseDto(meetingRepository.findFirstByStartDateTimeAfterOrderByStartDateTimeAsc(LocalDateTime.now()).orElseThrow());
    }
}
