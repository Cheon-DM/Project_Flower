package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.selfmade.*;
import project.flower.domain.member.Member;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerSingleRepository;
import project.flower.repository.SelfFlowerBouquetRepository;
import project.flower.repository.SelfFlowerItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowerSingleService {

    private final BusinessRepository businessRepository;
    private final FlowerSingleRepository flowerSingleRepository;

    private final SelfFlowerBouquetRepository selfFlowerBouquetRepository;

    private final SelfFlowerItemRepository selfFlowerItemRepository;

    public Map<Long, List<FlowerSingle>> findSingleList() {
        List<Business> businessList = businessRepository.findAll();
        Map<Long, List<FlowerSingle>> singleMap = new HashMap<>();
        for (Business business : businessList) {
            List<FlowerSingle> singleList = flowerSingleRepository.findAllByBusiness(business);
            for (FlowerSingle single : singleList) {
                log.info("single list={}",single.getName());
            }
        }

        return singleMap;
    }

    public Long makeSelfBouquet(Member member, Business business){

        SelfFlowerBouquet selfFlowerBouquet = SelfFlowerBouquet.builder()
                .business(business)
                .member(member)
                .totalPrice(0)
                .build();

        return selfFlowerBouquetRepository.save(selfFlowerBouquet).getId();


    }

    public SelfFlowerBouquet findSelfFlowerBouquet(Long id){
        SelfFlowerBouquet selfFlowerBouquet = selfFlowerBouquetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));

        return selfFlowerBouquet;
    }

    public Long addItem(SelfFlowerItemForm form, SelfFlowerBouquet bouquet, FlowerSingle single){

        SelfFlowerItem selfFlowerItem = form.toEntity(bouquet, single);
        selfFlowerItem.setSelfBouquet(bouquet);
        bouquet.addPrice(selfFlowerItem);


        return selfFlowerItemRepository.save(selfFlowerItem).getId();
    }

    public SelfFlowerItem findSelfFlowerItem(Long id){
        SelfFlowerItem selfFlowerItem = selfFlowerItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));

        return selfFlowerItem;
    }

    public Page<FlowerSingle> flowerSingleList(Pageable pageable){
        return flowerSingleRepository.findAll(pageable);
    }

    public Page<FlowerSingle> singleSearchListByName(String search, Pageable pageable){
        return flowerSingleRepository.findByNameContaining(search, pageable);
    }

    public Page<FlowerSingle> singleSearchListByLang(String search, Pageable pageable){
        return flowerSingleRepository.findByFlowerLangContaining(search, pageable);
    }

    public Page<FlowerSingle> singleSearchListByNameAndColor(String search, FlowerColor color, Pageable pageable){
        return flowerSingleRepository.findByNameContainingAndColor(search, color, pageable);
    }

    public Page<FlowerSingle> singleSearchListByLangAndColor(String search, FlowerColor color, Pageable pageable){
        return flowerSingleRepository.findByFlowerLangContainingAndColor(search, color, pageable);
    }
}
