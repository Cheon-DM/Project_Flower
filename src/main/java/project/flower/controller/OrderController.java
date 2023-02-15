package project.flower.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.flower.domain.member.MemberDetails;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("member/order")
    public String order(@AuthenticationPrincipal MemberDetails memberDetails,
                        HttpServletRequest request, Model model){
        String str = request.getParameter("list");
        List<String> strSplit = List.of(str.split(","));

        List<Long> orderList = strSplit.stream().map(Long::parseLong).toList();
        log.info(str);
        log.info("orderList={}",orderList.toString());
        model.addAttribute("orderList", orderList);
        return "member/order";
    }
}
