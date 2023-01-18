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
    public String signUpCustomerForm(@ModelAttribute("form") MemberForm form) {
        return "basic/signup/customer";
    }

    @PostMapping("/signupProc")
    public String signUpProc(MemberForm form) {
        log.info("name = {}, email = {}, password = {}, age = {}, sex = {}",
                form.getName(), form.getEmail(), form.getPassword(), form.getAge(), form.getSex());
        memberService.join(form);

        return "basic/login/customer";
    }

    @GetMapping("/login/customer")
    public String customerLogin() {
        return "basic/login/customer";
    }

//    @PostMapping("/loginProc")
//    public String customerLoginProc() {
//        return "basic/cart";
//    }
}
