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
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.member.MemberDetails;
import project.flower.service.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomepageController {

    private final BusinessService businessService;
    private final FavoriteService favoriteService;

    private final FlowerService flowerService;

    @GetMapping ("/")
    public String homePage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        // key : 부케, value : 가게
        Map<FlowerBouquet, Business> bouquetMap = flowerService.findBouquetList();
        model.addAttribute("bouquetMap", bouquetMap);

        // key : 싱글, value : 가게
        Map<FlowerSingle, Business> singleMap = flowerService.findSingleList();
        model.addAttribute("singleMap", singleMap);

        if (memberDetails != null){
            log.info(memberDetails.getMember().getName());
            model.addAttribute("name", memberDetails.getMemberName());
            model.addAttribute("member", memberDetails.getMember());
        } else {
            model.addAttribute("name", "guest");
            model.addAttribute("member", null);
        }

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
