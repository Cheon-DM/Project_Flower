package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.admin.Business;
import project.flower.domain.admin.BusinessForm;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.bouquet.FlowerBouquetForm;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.FlowerSingleForm;
import project.flower.domain.member.Member;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;
import project.flower.repository.FlowerSingleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;

    private final FlowerBouquetRepository flowerBouquetRepository;
    private final FlowerSingleRepository flowerSingleRepository;
    public Long registerBusiness(BusinessForm form, Member member){

        Business business = form.toEntity(member);
        business.setMember(member);

        return businessRepository.save(business).getId();
    }
    @Transactional
    public Business findBusiness(Long id){

        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 꽃집이 존재하지 않습니다."));

        return business;
    }

    @Transactional
    public Map<Long, Business> findBusinessMap(){
        Map<Long, Business> businessMap = new HashMap<>();
        List<Business> businessList = businessRepository.findAll();
        for (Business business : businessList) {
            businessMap.put(business.getId(), business);
        }
        return businessMap;
    }

    public Long registerBouquet(FlowerBouquetForm form, Business business){

        FlowerBouquet bouquet = form.toEntity(business);
        bouquet.setBusiness(business);

        return flowerBouquetRepository.save(bouquet).getId();
    }

    @Transactional
    public Long registerSingle(FlowerSingleForm form, Business business){

        FlowerSingle single = form.toEntity(business);
        single.setBusiness(business);

        return flowerSingleRepository.save(single).getId();
    }

}
