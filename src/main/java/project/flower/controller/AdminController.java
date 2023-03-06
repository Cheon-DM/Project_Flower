package project.flower.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.admin.Business;
import project.flower.domain.member.MemberDetails;
import project.flower.domain.member.MemberForm;
import project.flower.domain.order.FlowerOrderItem;
import project.flower.service.MemberService;
import project.flower.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping("/signup/seller")
    public String signUpSellerForm(@ModelAttribute("form") MemberForm form){
        return "signup/seller";
    }

    @PostMapping("/signup/seller")
    public  String signUpSeller(@Valid @ModelAttribute("form") MemberForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // 회원가입 페이지로 다시 이동
            return "signup/seller";
        }

        // 중복 체크
        try {
            memberService.validateDuplicateMember(form.toUserEntity());
        } catch (Exception e) {
            bindingResult.addError(new FieldError("form", "email", e.getMessage()));
            return "signup/seller";
        }

        // 성공로직
        memberService.joinAdmin(form);
        return "redirect:/login";
    }

    @GetMapping("admin/order/{businessId}")
    public String showOrder(@PathVariable Long businessId,
                            @AuthenticationPrincipal MemberDetails memberDetails, Model model){
        List<Business> businessList = memberDetails.getMember().getBusinessList();
        Map<LocalDateTime, List<FlowerOrderItem>> orderMap = orderService.showOrder_Admin(businessId);

        model.addAttribute("orderMap", orderMap);
        model.addAttribute("businessList", businessList);

        return "admin/order";
    }

    @PostMapping(value = "admin/order/{flowerId}")
    @ResponseBody
    public void changeOrderStatus_delivery(HttpServletRequest request){
        String orderId = request.getParameter("order");

        orderService.inDelivery(orderId);
        return;
    }

    @PostMapping(value = "admin/orderComplete/{flowerId}")
    @ResponseBody
    public void changeOrderStatus_complete(HttpServletRequest request){
        String orderId = request.getParameter("order");

        orderService.complete(orderId);
        return;
    }
}
