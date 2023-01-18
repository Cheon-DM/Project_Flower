package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.admin.AdminForm;
import project.flower.service.AdminService;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/signup/seller")
    public String signUpSellerForm(){

        return "signup/seller";
    }

    @PostMapping("/signup/seller")
    public  String signUpSeller(@ModelAttribute AdminForm form){
        log.info("email = {}, password = {}, name = {}, bus_num ={}, bus_name={}, age={}", form.getName(),
                form.getPassword(), form.getName(), form.getBusinessNum(), form.getBusinessName(),form.getAge());
        adminService.createAdmin(form);
        return "redirect:/";
    }

    @GetMapping("/signin/seller")
    public String signInSellerForm(@ModelAttribute AdminForm form){

        return "signin/seller";
    }

    @PostMapping("/signin/seller")
    public String signInSeller(@ModelAttribute AdminForm form){
        log.info("email = {}, password = {}", form.getEmail(), form.getPassword());
        adminService.signInAdmin(form.getEmail(), form.getPassword());
        return "redirect:/";
    }

}
