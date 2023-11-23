package group.meetmix.service.meeting;

import group.meetmix.data.dto.MeetingDto;
import group.meetmix.data.entity.MeetingEntity;

import java.util.List;

public interface MeetingService {
    public void saveMeeting(MeetingDto meetingDto);
    public List<MeetingDto> findAllMeetings();
    public  MeetingDto findMeetingById(Long meetingId);
    public void updateMeeting(MeetingDto meetingDto);
    public void deleteMeeting(Long meetingId);
    public List<MeetingDto> findALlById(Long id);
}
