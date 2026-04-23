package optis.guideme.application;

import optis.guideme.api.dto.AttendeeDto;
import optis.guideme.api.dto.MeetingDto;
import optis.guideme.domain.meeting.Meeting;
import optis.guideme.domain.meeting.MeetingId;
import optis.guideme.domain.meeting.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MeetingService {
private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting createNewMeeting(MeetingDto meetingDto){
        Meeting meeting =  new Meeting(
                MeetingId.of(UUID.randomUUID()),
                meetingDto.endDateTime(),
                meetingDto.name(),
                meetingDto.occured(),
                meetingDto.referenceCode(),
                meetingDto.sharedUsers().stream().map(AttendeeDto::toDomain).toList(),
                meetingDto.startDateTime()
        );
        return meetingRepository.save(meeting);
    }
}
