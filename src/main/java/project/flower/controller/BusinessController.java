package project.flower.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.flower.domain.admin.Business;
import project.flower.domain.admin.BusinessForm;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.bouquet.FlowerBouquetForm;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.FlowerSingleForm;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberDetails;
import project.flower.service.BusinessService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping("/admin/registerbusiness")
    public String registerForm(){
        return "admin/registerbusiness";
    }

    @PostMapping("/admin/registerbusiness")
    public String register(@ModelAttribute("form") BusinessForm form, BindingResult bindingResult,
                           @AuthenticationPrincipal MemberDetails memberDetails, Model model) throws IOException {

        log.info("businessName = {}, num = {}",
                form.getBusinessName(), form.getBusinessNum());

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);

            return "/admin/registerbusiness";
        }
        Member currentMember = memberDetails.getMember();
        businessService.registerBusiness(form, currentMember);

        return "redirect:/adminpage";
    }

    @GetMapping("/admin/businesses")
    public String businessList(@AuthenticationPrincipal MemberDetails memberDetails, Model model){

        List<Business> businessList = memberDetails.getMember().getBusinessList();
        model.addAttribute("businessList", businessList);

        return "admin/businesses";
    }

    @GetMapping("/admin/businesses/{businessId}")
    public String flowerList(@PathVariable long businessId, Model model){

        Business business = businessService.findBusiness(businessId);
        List<FlowerBouquet> bouquetList = business.getBouquetList();
        List<FlowerSingle> singleList = business.getSingleList();
        model.addAttribute("bouquetList", bouquetList);
        model.addAttribute("singleList", singleList);
        model.addAttribute("business", business);

        return "admin/business";
    }

    @GetMapping("/admin/businesses/{businessId}/bouquet")
    public String registerBouquetForm(@PathVariable long businessId, @ModelAttribute("form") FlowerBouquetForm form, Model model){
        Business business = businessService.findBusiness(businessId);
        model.addAttribute("business", business);
        model.addAttribute("flowercolor", FlowerColor.values());
        return "admin/flower/bouquet";
    }

    @PostMapping("/admin/businesses/{businessId}/bouquet")
    public String registerBouquet(@PathVariable long businessId,
                                  @ModelAttribute("form") FlowerBouquetForm form,  BindingResult bindingResult, Model model) throws Exception {

        log.info("name = {}, detail = {}, price = {}, stock = {}, color = {}",
                form.getName(), form.getBouquetDetail(), form.getPrice(), form.getStock(), form.getColor());

        log.info("image = {}", form.getImgFile());
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);

            return "redirect:/admin/businesses/{businessId}";
        }
        Business bus = businessService.findBusiness(businessId);
        businessService.registerBouquet(form ,bus);


        return "redirect:/admin/businesses/{businessId}";
    }

    @GetMapping("/admin/businesses/{businessId}/single")
    public String registerSingleForm(@PathVariable long businessId, @ModelAttribute("form") FlowerSingleForm form, Model model){
        Business business = businessService.findBusiness(businessId);
        model.addAttribute("business", business);
        model.addAttribute("flowercolor", FlowerColor.values());
        return "admin/flower/single";
    }

    @PostMapping("/admin/businesses/{businessId}/single")
    public String registerSingle(@PathVariable long businessId, @ModelAttribute("business") Business business,
                                  @ModelAttribute("form") FlowerSingleForm form, BindingResult bindingResult) throws IOException {

        log.info("name = {}, lang = {}, price = {}, stock = {}, color = {}",
                form.getName(), form.getFlowerLang(), form.getPrice(), form.getStock(), form.getColor());

        log.info("image = {}", form.getImgFile());

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);

            return "redirect:/admin/businesses/{businessId}/single";
        }

        Business bus = businessService.findBusiness(businessId);
        businessService.registerSingle(form ,bus);

        return "redirect:/admin/businesses/{businessId}";
    }
}
