package group.meetmix.data.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long memberId;
    private String memberEmail;
    private String password;
    private String nickname;
}

