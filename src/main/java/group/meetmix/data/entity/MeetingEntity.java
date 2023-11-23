package group.meetmix.data.entity;

import group.meetmix.data.dto.MeetingDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEETING")
@Getter
@Setter
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private Long meetingId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;
    @Column(name = "DEADLINE")
    private LocalDateTime deadline;
    @Column(name="MAX_CAPACITY")
    private int maxCapacity;
    @OneToMany(mappedBy = "meeting")
    private List<ApplyEntity> applies = new ArrayList<>();

    // == 연관 관계 메서드 == //
    public void setMemberEntity(MemberEntity member){
        this.member = member;
        member.getMeetings().add(this);
    }

    // == 생성 메서드 == //
    public static MeetingEntity createMeeting(MemberEntity member, MeetingDto meetingDto){
        MeetingEntity meetingEntity = new MeetingEntity();
        meetingEntity.setMemberEntity(member);
        meetingEntity.setTitle(meetingDto.getTitle());
        meetingEntity.setContent(meetingDto.getContent());
        meetingEntity.setCreateAt(LocalDateTime.now());
        meetingEntity.setDeadline(meetingDto.getDeadline());
        meetingEntity.setMaxCapacity(meetingDto.getMaxCapacity());
        return  meetingEntity;
    }

}
