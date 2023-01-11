package project.flower.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.AdminForm;
import project.flower.domain.member.MemberForm;
import project.flower.service.JoinAdmin;
import project.flower.service.JoinMember;

@Slf4j
@Controller
@RequestMapping("/basic")
public class BasicController {

    private JoinAdmin joinAdmin;
    private JoinMember joinMember;

    @GetMapping("/signup/seller")
    public String signUpSellerForm(){

        return "basic/signup/seller";
    }

    @GetMapping("/signup/customer")
    public String signUpCustomerForm() {
        return "basic/signup/customer";
    }

    @PostMapping("/signup/seller")
    public String signUpSeller(@ModelAttribute AdminForm form){
        log.info("email = {}, password = {}, name = {}", form.getName(),
                form.getPassword(), form.getName());
        joinAdmin.join(form);
        return "basic/cart";
    }

    @PostMapping("/signup/customer")
    public String signUpCustomer(@ModelAttribute("memberForm") MemberForm form){
        log.info("name = {}, email = {}, password = {}, age = {}, sex = {}",
                form.getName(), form.getEmail(), form.getPassword(), form.getAge(), form.getSex());
        joinMember.join(form);
        return "basic/cart";
    }
}
