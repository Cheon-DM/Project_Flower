package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.member.MemberForm;
import project.flower.service.MemberService;

@Slf4j
@Controller
@RequestMapping("/basic")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup/customer")
    public String signUpCustomerForm() {
        return "basic/signup/customer";
    }

    @PostMapping("/signup/customer")
    public String signUpCustomer(@ModelAttribute("memberForm") MemberForm form){
        log.info("name = {}, email = {}, password = {}, age = {}, sex = {}",
                form.getName(), form.getEmail(), form.getPassword(), form.getAge(), form.getSex());
        memberService.createMember(form);
        return "redirect:/";
    }
}
