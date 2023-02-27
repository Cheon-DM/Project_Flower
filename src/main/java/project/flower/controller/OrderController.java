package project.flower.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.cart.CartItem;
import project.flower.domain.member.MemberDetails;
import project.flower.service.CartService;
import project.flower.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("member/order")
    public String order(HttpServletRequest request, Model model){
        String str = request.getParameter("list");
        List<String> strSplit = List.of(str.split(","));

        List<Long> orderList = strSplit.stream().map(Long::parseLong).toList();
        ArrayList<CartItem> items = new ArrayList<>();
        long total = 0;
        for (Long id : orderList) {
            CartItem item = cartService.findCartItem(id);
            total += item.getPrice();
            items.add(item);
        }
        model.addAttribute("itemList", orderList);
        model.addAttribute("total", total);
        model.addAttribute("orderItems", items);
        return "member/order";
    }

    @GetMapping("member/checkout")
    public String checkInfo(){
        return "member/checkout";
    }

    @PostMapping(value = "member/checkout")
    @ResponseBody
    public void checkout(@AuthenticationPrincipal MemberDetails memberDetails, HttpServletRequest request){
        log.info("== checkout start ==");
        String[] values = request.getParameterValues("ids[]");
        for (int i = 0; i < values.length; i++) {
            log.info(values[i]);
        }

        orderService.checkout(memberDetails.getMember(), values);
        return;
    }
}
