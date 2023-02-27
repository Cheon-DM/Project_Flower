package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.SelfFlowerBouquet;
import project.flower.domain.flower.selfmade.SelfFlowerItem;
import project.flower.domain.flower.selfmade.SelfFlowerItemForm;
import project.flower.domain.member.MemberDetails;
import project.flower.service.*;

import java.util.List;

@Slf4j
@Controller

@RequiredArgsConstructor

public class DiyController {

    private final BusinessService businessService;
    private final MemberService memberService;
    private final FlowerService flowerService;
    private final FlowerSingleService flowerSingleService;

    private final CartService cartService;


    @GetMapping("/diyshop/business/{businessId}")
    public String diyshopPage(@PathVariable long businessId, Model model){

        Business business = businessService.findBusiness(businessId);
        List<FlowerSingle> singleList = business.getSingleList();
        model.addAttribute("business", business);
        model.addAttribute("singleList", singleList);
        return "shop/diybusinessdetail";
    }

    @GetMapping("singleflower/{singleId}")
    public String singleDetail( @PathVariable long singleId,@AuthenticationPrincipal MemberDetails memberDetails, Model model){
        FlowerSingle single = flowerService.findSingle(singleId);
        model.addAttribute("single", single);
        model.addAttribute("cartItemCount", cartService.showItemCount(memberDetails.getMember()));
        return "shop/single";
    }

    @GetMapping("/diybouquet/business/{businessId}")
    public String diyBouquetFirstPage(@PathVariable long businessId, @AuthenticationPrincipal MemberDetails memberDetails, Model model){

        Business business = businessService.findBusiness(businessId);
        Long selfFlowerBouquetId = flowerSingleService.makeSelfBouquet(memberDetails.getMember(), business);
        List<FlowerSingle> singleList = business.getSingleList();
        SelfFlowerBouquet selfFlowerBouquet = flowerSingleService.findSelfFlowerBouquet(selfFlowerBouquetId);
        model.addAttribute("singleList", singleList);
        model.addAttribute("selfFlowerBouquet", selfFlowerBouquet);
        model.addAttribute("business", business);

        return "shop/diybouquet";
    }

    @GetMapping("/diybouquet/business/{businessId}/diy/{selfFlowerBouquetId}")
    public String diyBouquetPage(@PathVariable long businessId,@PathVariable long selfFlowerBouquetId, Model model){
        Business business = businessService.findBusiness(businessId);
        List<FlowerSingle> singleList = business.getSingleList();
        SelfFlowerBouquet selfFlowerBouquet = flowerSingleService.findSelfFlowerBouquet(selfFlowerBouquetId);
        List<SelfFlowerItem> selfFlowerItemList = selfFlowerBouquet.getSelfFlowerItemList();
        model.addAttribute("business", business);
        model.addAttribute("singleList", singleList);
        model.addAttribute("selfFlowerBouquet", selfFlowerBouquet);
        model.addAttribute("selfFlowerItemList", selfFlowerItemList);
        return "shop/diybouquet";
    }



    /*@GetMapping("/add/diy/{selfFlowerBouquetId}/single/{singleId}")
    public String addDiyFlower(@PathVariable long selfFlowerBouquetId, @PathVariable long singleId){
        log.info("bouquetId = {}, singleID = {}",
                selfFlowerBouquetId, singleId);
        SelfFlowerBouquet selfFlowerBouquet = flowerSingleService.findSelfFlowerBouquet(selfFlowerBouquetId);
        FlowerSingle single = flowerService.findSingle(singleId);
        flowerSingleService.addItem(selfFlowerBouquet, single);
        return "redirect:/"; //??
    }*/

    @GetMapping("/detail/{businessId}/diy/{selfFlowerBouquetId}/single/{singleId}")
    public String detailFlowerForm(@PathVariable long selfFlowerBouquetId, @PathVariable long singleId, Model model, @ModelAttribute("form") SelfFlowerItemForm form){
        FlowerSingle single = flowerService.findSingle(singleId);
        Business business = single.getBusiness();
        SelfFlowerBouquet selfFlowerBouquet = flowerSingleService.findSelfFlowerBouquet(selfFlowerBouquetId);
        model.addAttribute("single", single);
        model.addAttribute("selfFlowerBouquet", selfFlowerBouquet);
        model.addAttribute("business", business);
        return"shop/addsingle";
    }
    @PostMapping("/detail/{businessId}/diy/{selfFlowerBouquetId}/single/{singleId}")
    public String addFlower(@PathVariable long selfFlowerBouquetId, @PathVariable long singleId, @ModelAttribute("form") SelfFlowerItemForm form, Model model, @PathVariable String businessId){
        log.info("count ={}",form.getCount());
        SelfFlowerBouquet selfFlowerBouquet = flowerSingleService.findSelfFlowerBouquet(selfFlowerBouquetId);
        FlowerSingle single = flowerService.findSingle(singleId);

        flowerSingleService.addItem(form, selfFlowerBouquet, single);

        return "redirect:/diybouquet/business/{businessId}/diy/{selfFlowerBouquetId}";
    }

    @GetMapping("/edit/{businessId}/diy/{selfFlowerBouquetId}")
    public String editQuantityPage(@PathVariable long selfFlowerBouquetId, @PathVariable long businessId, @ModelAttribute("form") SelfFlowerItemForm form, Model model){

        Business business = businessService.findBusiness(businessId);
        List<FlowerSingle> singleList = business.getSingleList();
        SelfFlowerBouquet selfFlowerBouquet = flowerSingleService.findSelfFlowerBouquet(selfFlowerBouquetId);
        List<SelfFlowerItem> selfFlowerItemList = selfFlowerBouquet.getSelfFlowerItemList();

        model.addAttribute("singleList", singleList);
        model.addAttribute("selfFlowerItemList", selfFlowerItemList);

        return"shop/edit";
    }

    @PostMapping("/edit/{businessId}/diy/{selfFlowerBouquetId}/single/{singleId}")
    public String editQuantity(@PathVariable long selfFlowerBouquetId, @PathVariable long singleId, @ModelAttribute("form") SelfFlowerItemForm form, Model model){


        return"shop/edit";
    }


}
