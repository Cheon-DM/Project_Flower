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
        log.info("name = {}, email = {}, password = {}, age = {}, sex = {}",
                form.getName(), form.getEmail(), form.getPassword(), form.getAge(), form.getSex());

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);

            // 회원가입 페이지로 다시 이동
            return "signup/customer";
        }

        // 중복 체크
        try {
            memberService.validateDuplicateMember(form.toUserEntity());
        } catch (Exception e) {
            bindingResult.addError(new FieldError("form", "email", e.getMessage()));
            return "signup/customer";
        }

        // 성공로직
        memberService.joinAdmin(form);
        return "redirect:/login";
    }

    @GetMapping("admin/order")
    public String showOrder(@AuthenticationPrincipal MemberDetails memberDetails, Model model){
        List<Business> businessList = memberDetails.getMember().getBusinessList();
        Map<String, List<FlowerOrderItem>> orderMap = orderService.showOrder_Admin(businessList);
        model.addAttribute("orderMap", orderMap);

        return "admin/order";
    }

    @PostMapping(value = "admin/order/{flowerId}")
    @ResponseBody
    public void changeOrderStatus_delivery(HttpServletRequest request){
        log.info("== processing -> in delivery ==");
        String orderId = request.getParameter("order");
        log.info(orderId);

        orderService.inDelivery(orderId);
        return;
    }

    @PostMapping(value = "admin/orderComplete/{flowerId}")
    @ResponseBody
    public void changeOrderStatus_complete(HttpServletRequest request){
        log.info("== processing -> in delivery ==");
        String orderId = request.getParameter("order");
        log.info(orderId);

        orderService.complete(orderId);
        return;
    }
}
