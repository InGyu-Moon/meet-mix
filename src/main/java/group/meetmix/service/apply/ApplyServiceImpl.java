package group.meetmix.service.apply;

import group.meetmix.data.dto.ApplyDto;
import group.meetmix.data.entity.ApplyEntity;
import group.meetmix.data.entity.MeetingEntity;
import group.meetmix.data.entity.MemberEntity;
import group.meetmix.data.repository.ApplyRepository;
import group.meetmix.data.repository.MeetingRepository;
import group.meetmix.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
