package group.meetmix.data.entity;

import group.meetmix.data.repository.ApplyRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "APPLY")
@Getter
@Setter
public class ApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLY_ID")
    private Long applyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEETING_ID")
    private MeetingEntity meeting;

    // == 연관 관계 메서드 == //
    public void setMemberEntity(MemberEntity member){
        this.member = member;
        member.getApplies().add(this);
    }
    // == 연관 관계 메서드 == //
    public void setMeetingEntity(MeetingEntity meeting){
        this.meeting = meeting;
        meeting.getApplies().add(this);
    }
    // == 생성 메서드 == //
    public static ApplyEntity createApply(MemberEntity member, MeetingEntity meeting){
        ApplyEntity applyEntity = new ApplyEntity();
        applyEntity.setMember(member);
        applyEntity.setMeeting(meeting);
        return applyEntity;
    }

}
