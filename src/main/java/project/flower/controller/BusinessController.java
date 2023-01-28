package project.flower.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.flower.domain.admin.BusinessForm;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberDetails;
import project.flower.domain.member.MemberForm;
import project.flower.repository.BusinessRepository;
import project.flower.service.BusinessService;
import project.flower.service.MemberService;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class BusinessController {

    private final MemberService memberService;

    private final BusinessService businessService;

    @GetMapping("/admin/registerbusiness")
    public String registerForm(){
        return "admin/registerbusiness";
    }

    @PostMapping("/admin/registerbusiness")
    public String register(@ModelAttribute("form") BusinessForm form, BindingResult bindingResult,
                           @AuthenticationPrincipal MemberDetails memberDetails, Model model){

        log.info("businessName = {}, num = {}",
                form.getBusinessName(), form.getBusinessNum());

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);

            // 회원가입 페이지로 다시 이동
            return "/admin/registerbusiness";
        }
        Member currentMember = memberDetails.getMember();
        businessService.registerBusiness(form, currentMember);

        return "adminpage";
    }
}
