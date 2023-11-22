package group.meetmix.service.meeting;

import group.meetmix.data.dto.MeetingDto;
import group.meetmix.data.entity.MeetingEntity;
import group.meetmix.data.entity.MemberEntity;
import group.meetmix.data.repository.MeetingRepository;
import group.meetmix.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService{
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public void saveMeeting(MeetingDto meetingDto) {

        Optional<MemberEntity> memberOptional = memberRepository.findById(meetingDto.getMemberId());
        MeetingEntity meeting = MeetingEntity.createMeeting(memberOptional.get(), meetingDto);
        meetingRepository.save(meeting);
    }

    @Override
    public List<MeetingDto> findAllMeetings() {
        List<MeetingEntity> meetingEntityList = meetingRepository.findAll();

        List<MeetingDto> meetingDtoList = new ArrayList<>();

        for (MeetingEntity meetingEntity : meetingEntityList) {
            MeetingDto meetingDto = new MeetingDto();

            // meetingEntity 를 meetingDto 에 복사합니다.
            convertMeetingEntityToMeetingDto(meetingEntity, meetingDto);

            meetingDtoList.add(meetingDto);
        }
        return meetingDtoList;
    }
    @Override
    public MeetingDto findMeetingById(Long meetingId) {
        MeetingEntity meetingEntity = meetingRepository.findById(meetingId);
        MeetingDto meetingDto = new MeetingDto();

        convertMeetingEntityToMeetingDto(meetingEntity, meetingDto);

        return meetingDto;
    }

    @Override
    public void updateMeeting(MeetingDto meetingDto) {
        MeetingEntity meetingEntity = meetingRepository.findById(meetingDto.getMeetingId());
        meetingEntity.setTitle(meetingDto.getTitle());
        meetingEntity.setContent(meetingDto.getContent());
        meetingEntity.setMaxCapacity(meetingDto.getMaxCapacity());
        meetingRepository.save(meetingEntity);
    }

    @Override
    public void deleteMeeting(Long meetingId) {
        MeetingEntity meetingEntity = meetingRepository.findById(meetingId);
        meetingRepository.delete(meetingEntity);
    }

    private static void convertMeetingEntityToMeetingDto(MeetingEntity meetingEntity, MeetingDto meetingDto) {
        meetingDto.setMeetingId(meetingEntity.getMeetingId());
        meetingDto.setTitle(meetingEntity.getTitle());
        meetingDto.setContent(meetingEntity.getContent());
        meetingDto.setCreateAt(meetingEntity.getCreateAt());
        meetingDto.setDeadline(meetingEntity.getDeadline());
        meetingDto.setMaxCapacity(meetingEntity.getMaxCapacity());
        meetingDto.setNickname(meetingEntity.getMember().getNickname());
        meetingDto.setMemberId(meetingEntity.getMember().getMemberId());
    }


}
