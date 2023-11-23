package group.meetmix.data.dto;

import lombok.Data;

@Data
public class ApplyDto {
    private Long meetingId;
    private Long memberId;

    private String Title;
}
