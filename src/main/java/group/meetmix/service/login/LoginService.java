package group.meetmix.service.login;

import group.meetmix.data.dto.MemberDto;

public interface LoginService {
    public MemberDto login(String loginId, String password);
}
