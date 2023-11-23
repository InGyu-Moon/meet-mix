package group.meetmix.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(unique = true,name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name = "PASSWORD")
    private String password;
    @Column(unique = true,name = "NICKNAME")
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<MeetingEntity> meetings = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<ApplyEntity> applies = new ArrayList<>();
}
