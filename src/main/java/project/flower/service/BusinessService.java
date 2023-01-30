package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.admin.Business;
import project.flower.domain.admin.BusinessForm;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.bouquet.FlowerBouquetForm;
import project.flower.domain.member.Member;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;

    private final FlowerBouquetRepository flowerBouquetRepository;

    public Long registerBusiness(BusinessForm form, Member member){

        Business business = form.toEntity(member);
        business.setMember(member);

        return businessRepository.save(business).getId();
    }

    public Business findBusiness(Long id){

        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 꽃집이 존재하지 않습니다."));

        return business;
    }

    public List<Business> findAllBusiness(){
        return businessRepository.findAll();
    }

    public Long registerBouquet(FlowerBouquetForm form, Business business){

        FlowerBouquet bouquet = form.toEntity(business);
        bouquet.setBusiness(business);

        return flowerBouquetRepository.save(bouquet).getId();
    }

}
