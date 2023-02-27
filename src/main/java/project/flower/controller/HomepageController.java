package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import project.flower.domain.Role;
import project.flower.domain.admin.Business;
import project.flower.domain.favorite.FavoriteStore;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.member.MemberDetails;
import project.flower.domain.order.FlowerOrder;
import project.flower.domain.order.FlowerOrderItem;
import project.flower.file.FileStore;
import project.flower.service.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomepageController {

    private final CartService cartService;
    private final FavoriteService favoriteService;
    private final BusinessService businessService;
    private final FlowerService flowerService;
    private final OrderService orderService;

    private final FileStore fileStore;

    @GetMapping (value = "/")
    public String homePage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {



        // key : 부케, value : 가게
        Map<FlowerBouquet, Business> bouquetMap = flowerService.findBouquetList();
        model.addAttribute("bouquetMap", bouquetMap);

        // key : 싱글, value : 가게
        Map<FlowerSingle, Business> singleMap = flowerService.findSingleList();
        model.addAttribute("singleMap", singleMap);

        if (memberDetails != null){
            if (memberDetails.getMember().getRole().equals(Role.ROLE_ADMIN)){
                model.addAttribute("name", memberDetails.getMember().getName());
                model.addAttribute("member", memberDetails.getMember());
            }
            else {
                model.addAttribute("cartItemCount", cartService.showItemCount(memberDetails.getMember()));
                model.addAttribute("name", memberDetails.getMember().getName());
                model.addAttribute("member", memberDetails.getMember());
            }
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
        List<List<FlowerOrderItem>> orderList = orderService.findOrder(memberDetails.getMember());
        model.addAttribute("member", memberDetails.getMember());
        model.addAttribute("storeList", favoriteStoreList);
        model.addAttribute("orderList", orderList);
        return "mypage";
    }

    @GetMapping("/adminpage")
    public String adminPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        model.addAttribute("member", memberDetails.getMember());
        return "adminpage";
    }


    @GetMapping("/diybouquetpage")
    public String diyPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        model.addAttribute("member", memberDetails.getMember());
        Map<Long, Business> businessMap = businessService.findBusinessMap();
        model.addAttribute("businessMap", businessMap);

        return "diybouquetpage";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {

        return new UrlResource("file:/" + fileStore.getFullPath(filename));
    }

}
