package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.flower.domain.admin.Business;
import project.flower.domain.favorite.FavoriteStore;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.member.MemberDetails;
import project.flower.service.BusinessService;
import project.flower.service.FavoriteService;
import project.flower.service.FlowerBouquetService;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomepageController {

    private final BusinessService businessService;
    private final FavoriteService favoriteService;
    private final FlowerBouquetService bouquetService;

    @GetMapping ("/")
    public String homePage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        List<Business> businessList = businessService.findAllBusiness();
        model.addAttribute("businessList", businessList);

        Map<Long, List<FlowerBouquet>> bouquetMap = bouquetService.findBouquetList();
        model.addAttribute("bouquetMap", bouquetMap);

        model.addAttribute("member", memberDetails);

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
        List<FavoriteStore> favoriteStoreList = favoriteService.findFavoriteStoreAll(memberDetails.getMember());
        model.addAttribute("member", memberDetails.getMember());
        model.addAttribute("storeList", favoriteStoreList);
        return "mypage";
    }

    @GetMapping("/adminpage")
    public String adminPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        model.addAttribute("member", memberDetails.getMember());
        return "adminpage";
    }
}
