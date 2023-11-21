package group.meetmix.service.member;

import group.meetmix.data.dto.MemberDto;
import group.meetmix.data.dto.MemberInputDto;

public interface MemberService {
    public void saveMember(MemberInputDto userInputDto);
    // userEmail 중복 검사
    public boolean isEmailUnique(String userEmail);

    // nickname 중복 검사
    public boolean isNicknameUnique(String nickname);
    public MemberDto getMemberByMemberId(Long memberId);

}
