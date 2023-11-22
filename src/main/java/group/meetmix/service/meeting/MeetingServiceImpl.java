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

        Optional<MemberEntity> memberOptional = memberRepository.findById(meetingDto.getMeetingId());
        MeetingEntity meeting = MeetingEntity.createMeeting(memberOptional.get(), meetingDto);
        meetingRepository.save(meeting);
    }

    @Override
    public List<MeetingDto> findAllMeetings() {
        List<MeetingEntity> meetingEntityList = meetingRepository.findAll();

        List<MeetingDto> meetingDtoList = new ArrayList<>();

        for (MeetingEntity meetingEntity : meetingEntityList) {
            MeetingDto meetingDto = new MeetingDto();
            // PostEntity를 PostDto로 복사합니다.
            BeanUtils.copyProperties(meetingEntity, meetingDto);
            meetingDto.setNickname(meetingEntity.getMember().getNickname());
            meetingDtoList.add(meetingDto);
        }
        return meetingDtoList;
    }
}
