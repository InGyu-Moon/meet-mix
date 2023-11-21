package group.meetmix.service.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import group.meetmix.data.dto.MemberDto;
import group.meetmix.data.entity.MemberEntity;
import group.meetmix.data.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public MemberDto login(String memberEmail, String password) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail)
                .filter(m->bCryptPasswordEncoder.matches(password,m.getPassword()))
                .orElse(null);

        if (memberEntity == null) {
            return null;
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(memberEntity.getMemberId());
        memberDto.setMemberEmail(memberEntity.getMemberEmail());
        memberDto.setPassword(memberEntity.getPassword());
        memberDto.setNickname(memberEntity.getNickname());

        return memberDto;

    }
}
