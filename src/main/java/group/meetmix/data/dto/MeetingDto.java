package group.meetmix.data.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingDto {
    private Long meetingId;
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime deadline;
    @Min(value = 1, message = "최대인원은 1 이상이어야 합니다.")
    private int maxCapacity;
    private String nickname;
    private Long memberId;
}
