package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.admin.Business;
import project.flower.domain.admin.BusinessForm;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberDetails;
import project.flower.service.BusinessService;
import project.flower.service.MemberService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class BusinessController {

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

    @GetMapping("/admin/businesses")
    public String businessList(@AuthenticationPrincipal MemberDetails memberDetails, Model model){

        List<Business> businessList = memberDetails.getMember().getBusinessList();
        model.addAttribute("businessList", businessList);

        return "admin/businesses";
    }

    @GetMapping("/admin/businesses/{businessId}")
    public String flowerList(@PathVariable long businessId, Model model){

        Business business = businessService.findBusiness(businessId);
        model.addAttribute("business", business);

        return "admin/business";
    }



}
