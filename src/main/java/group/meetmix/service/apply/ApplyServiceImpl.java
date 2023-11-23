package group.meetmix.service.apply;

import group.meetmix.data.dto.ApplyDto;
import group.meetmix.data.dto.MeetingDto;
import group.meetmix.data.entity.ApplyEntity;
import group.meetmix.data.entity.MeetingEntity;
import group.meetmix.data.entity.MemberEntity;
import group.meetmix.data.repository.ApplyRepository;
import group.meetmix.data.repository.MeetingRepository;
import group.meetmix.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService{

    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    @Override
    public void saveApply(ApplyDto applyDto) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(applyDto.getMemberId());
        MeetingEntity meeting = meetingRepository.findById(applyDto.getMeetingId());

        ApplyEntity apply = ApplyEntity.createApply(optionalMember.get(), meeting);

        applyRepository.save(apply);
    }

    @Override
    public List<ApplyDto> findAllApplies(Long memberId) {
        List<ApplyEntity> applyEntityList = applyRepository.findAllAppliesByMemberId(memberId);

        List<ApplyDto> applyDtoList = new ArrayList<>();

        for (ApplyEntity applyEntity : applyEntityList) {
            ApplyDto applyDto = new ApplyDto();

            // ApplyEntity 를 applyDto 에 복사합니다.
            applyDto.setMeetingId(applyEntity.getMeeting().getMeetingId());
            applyDto.setMemberId(applyEntity.getMember().getMemberId());
            MeetingEntity meeting = meetingRepository.findById(applyDto.getMeetingId());
            applyDto.setTitle(meeting.getTitle());

            applyDtoList.add(applyDto);
        }
        return applyDtoList;
    }
}
