package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.flower.domain.member.MemberDetails;
import project.flower.service.FavoriteService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/add/{businessId}")
    public String addBusiness(@PathVariable Long businessId,
                              @AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        log.info("business id={}",businessId);
        log.info("user={}",memberDetails.getMember());
        model.addAttribute("user", memberDetails.getMember());
        favoriteService.saveStore(businessId, memberDetails.getMember());
        return "redirect:/";
    }

}
