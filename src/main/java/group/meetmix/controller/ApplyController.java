package group.meetmix.controller;

import group.meetmix.data.dto.ApplyDto;
import group.meetmix.data.dto.MeetingDto;
import group.meetmix.service.apply.ApplyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping
    public String makeApply(ApplyDto applyDto, @RequestBody Map<String, Long> requestBody, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }

        log.info(requestBody.toString());
        Long memberId = (Long)session.getAttribute("memberId");
        Long meetingId = requestBody.get("meetingId");

        applyDto.setMemberId(memberId);
        applyDto.setMeetingId(meetingId);
        applyService.saveApply(applyDto);

        return "redirect:/board";
    }
    @GetMapping("")
    public String findAllApplies(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }
        Long memberId = (Long)session.getAttribute("memberId");
        List<ApplyDto> applyDtoList = applyService.findAllApplies(memberId);

        model.addAttribute(applyDtoList);


        return null;
    }
}
