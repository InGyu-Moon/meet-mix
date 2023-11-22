package group.meetmix.controller;

import group.meetmix.data.dto.MeetingDto;
import group.meetmix.service.meeting.MeetingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final MeetingService meetingService;
    @GetMapping
    public String board(Model model,HttpServletRequest request){
        //postList 만들어서 addAttribute
        List<MeetingDto> meetingDtoList = meetingService.findAllMeetings();
        model.addAttribute("meetingDtoList",meetingDtoList);

        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }
        Object sessionNickname = session.getAttribute("nickname");
        model.addAttribute("sessionNickname",(String)sessionNickname);

        return "board/board";
    }

    @GetMapping("/new")
    public String newMeetingForm(@ModelAttribute MeetingDto meetingDto){
        return "board/newMeetingForm";
    }

    @PostMapping("/new")
    public String newMeeting(@Validated @ModelAttribute MeetingDto meetingDto, BindingResult result, HttpServletRequest request){
        // 입력폼 오류 확인
        if (result.hasErrors()) {
            return "board/newMeetingForm";
        }

        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }

        Long memberId = (Long)session.getAttribute("memberId");
        String nickname = (String)session.getAttribute("nickname");

        meetingDto.setMemberId(memberId);
        meetingDto.setNickname(nickname);

        log.info(String.valueOf(meetingDto));

        // 게시글 저장
        meetingService.saveMeeting(meetingDto);

        return "redirect:/board";
    }

    @GetMapping("/{meetingId}")
    public String boardInfo(@PathVariable Long meetingId, Model model, HttpServletRequest request ){

        //현재 세션에서 로그인된 사용자의 memberId를 meetingDto를 찾음
        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }
        MeetingDto meetingDto = meetingService.findMeetingById(meetingId);

        Long sessionMemberId = (Long)session.getAttribute("memberId");
        Long dtoMemberId = meetingDto.getMemberId();

        if(isCurrentUserAuthor(sessionMemberId,dtoMemberId))
            model.addAttribute("isCurrentUserAuthor",true);
        else if(!isCurrentUserAuthor(sessionMemberId,dtoMemberId))
            model.addAttribute("isCurrentUserAuthor",false);

        model.addAttribute("meetingDto",meetingDto);

        return "board/detail";
    }
    @GetMapping("/edit/{meetingId}")
    public String updateMeetingForm(@PathVariable Long meetingId, Model model ){
        MeetingDto meetingDto = meetingService.findMeetingById(meetingId);
        model.addAttribute("meetingDto",meetingDto);

        return "board/editMeetingForm";
    }

    @PostMapping("/edit/{meetingId}")
    public String updateMeeting(@PathVariable Long meetingId, Model model, @Validated @ModelAttribute MeetingDto meetingDto, BindingResult result ){
        // 입력폼 오류 확인
        if (result.hasErrors()) {
            return "board/newMeetingForm";
        }
        meetingDto.setMeetingId(meetingId);
        meetingService.updateMeeting(meetingDto);

        return "redirect:/board";
    }

    @GetMapping("/delete/{meetingId}")
    public String deletePost(@PathVariable Long meetingId ){
        meetingService.deleteMeeting(meetingId);
        return "redirect:/board";
    }

    public boolean isCurrentUserAuthor(Long sessionMemberId, Long dtoMemberId) {
        return sessionMemberId.equals(dtoMemberId);
    }

}
