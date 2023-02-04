package project.flower.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class DiyController {

    @GetMapping("/member/{memberId}/diybouquet/{businessId}")
    public String diyPage(@PathVariable long businessId, @PathVariable long memberId, Model model){

        return "member/diybouquet";
    }
}
