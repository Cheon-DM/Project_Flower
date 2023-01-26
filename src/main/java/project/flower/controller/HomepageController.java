package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.flower.domain.admin.Business;
import project.flower.domain.member.MemberDetails;
import project.flower.repository.BusinessRepository;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomepageController {

    private final BusinessRepository businessRepository;

    @GetMapping ("/")
    public String homePage(Model model) {
        List<Business> businessList = businessRepository.findAll();
        model.addAttribute("businessList", businessList);
        return "home";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        model.addAttribute("member", memberDetails.getMember());
        return "mypage";
    }
}
