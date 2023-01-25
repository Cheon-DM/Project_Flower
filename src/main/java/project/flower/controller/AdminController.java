package project.flower.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public  String signUpSeller(@Valid @ModelAttribute("form") AdminForm form, BindingResult bindingResult, Model model){
        log.info("email = {}, password = {}, name = {}, bus_num ={}, bus_name={}, age={}", form.getName(),
                form.getPassword(), form.getName(), form.getBusinessNum(), form.getBusinessName(),form.getAge());

        if(bindingResult.hasErrors()){
            //회원가입 실패 시 데이터 값 유지
            //하는거는 일단 나중에
            log.info("회원가입 실패");

            return "signup/seller";
        }

        adminService.join(form);
        log.info("회원가입 성공");
        return "redirect:/login/seller";
    }

    @GetMapping("/login/seller")
    public String signInSellerForm(@ModelAttribute AdminForm form){

        return "login/seller";
    }



}
