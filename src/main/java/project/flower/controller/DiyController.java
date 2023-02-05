package project.flower.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.service.BusinessService;
import project.flower.service.FlowerService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class DiyController {

    private final BusinessService businessService;
    private final FlowerService flowerService;

    @GetMapping("/diyshop/member/{memberId}/business/{businessId}")
    public String diyshopPage(@PathVariable long businessId, @PathVariable long memberId, Model model){

        Business business = businessService.findBusiness(businessId);
        List<FlowerSingle> singleList = business.getSingleList();
        model.addAttribute("business", business);
        model.addAttribute("singleList", singleList);
        return "shop/diybusinessdetail";
    }

    @GetMapping("/diybouquet/business/{businessId}")
    public String diyBouquetPage(Model model){
        return "shop/diybouquet";
    }

    @GetMapping("/diyshop/member/{memberId}/business/singleflower/{singleId}")
    public String singleDetail( @PathVariable long singleId, Model model){
        FlowerSingle single = flowerService.findSingle(singleId);
        model.addAttribute("single", single);
        return "shop/single";
    }
}
