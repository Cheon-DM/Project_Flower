package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.flower.domain.cart.CartItem;
import project.flower.domain.member.MemberDetails;
import project.flower.service.CartService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/add/bouquet/{bouquetId}")
    public String addBouquetToCart(@PathVariable("bouquetId") Long bouquetId, @AuthenticationPrincipal MemberDetails memberDetails,
                                Model model){
        log.info("item id = {}, user name = {}", bouquetId, memberDetails.getMemberName());
        model.addAttribute("user", memberDetails.getMember());
        cartService.saveBouquet(bouquetId, memberDetails.getMember());
        return "redirect:/";
    }

    @GetMapping("/add/single/{singleId}")
    public String addSingleToCart(@PathVariable("singleId") Long singleId, @AuthenticationPrincipal MemberDetails memberDetails,
                                   Model model){
        log.info("item id = {}, user name = {}", singleId, memberDetails.getMemberName());
        model.addAttribute("user", memberDetails.getMember());
        cartService.saveSingle(singleId, memberDetails.getMember());
        return "redirect:/";
    }

    @GetMapping("/member/cart")
    public String showCartItems(@AuthenticationPrincipal MemberDetails memberDetails, Model model){
        List<CartItem> cartItems = cartService.findCartItems(memberDetails.getMember());
        model.addAttribute("cartItems", cartItems);
        return "member/cart";
    }

    @GetMapping("/member/cart/{cartItemId}")
    public String deleteCartItem(@PathVariable Long cartItemId){
        cartService.deleteCartItem(cartItemId);
        return "redirect:/member/cart";
    }
}
