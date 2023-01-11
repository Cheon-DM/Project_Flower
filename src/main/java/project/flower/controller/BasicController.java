package project.flower.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.AdminForm;
import project.flower.service.JoinAdmin;

@Slf4j
@Controller
@RequestMapping("/basic")
public class BasicController {

    private JoinAdmin joinAdmin;

    @GetMapping("/signup/seller")
    public String signUpSellerForm(){

        return "basic/signup/seller";
    }

    @PostMapping("/signup/seller")
    public String signUpSeller(@ModelAttribute AdminForm form){
        log.info("email = {}, password = {}, name = {}", form.getName(),
                form.getPassword(), form.getName());
        joinAdmin.join(form);
        return "basic/cart";
    }

}
