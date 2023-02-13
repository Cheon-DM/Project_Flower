package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.bouquet.FlowerBouquetForm;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.FlowerSingleForm;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;
import project.flower.repository.FlowerSingleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class FlowerService {

    private final BusinessRepository businessRepository;

    private final FlowerBouquetRepository flowerBouquetRepository;
    private final FlowerSingleRepository flowerSingleRepository;

    @Transactional
    public FlowerSingle findSingle(Long id){
        FlowerSingle single = flowerSingleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 단품 꽃 없습니다."));

        return single;
    }

    @Transactional
    public Long editSingle(FlowerSingleForm form, Long id){
        FlowerSingle single = flowerSingleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없습니다."));
        single.update(form.getFlowerName(), form.getFlowerLang(), form.getColor(),form.getPrice(), form.getStock());

        return id;
    }

    @Transactional
    public FlowerBouquet findBouquet(Long id){
        FlowerBouquet bouquet = flowerBouquetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 부케 없습니다."));

        return bouquet;
    }

    @Transactional
    public Long editBouquet(FlowerBouquetForm form, Long id){
        FlowerBouquet bouquet = flowerBouquetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없습니다."));
        bouquet.update(form.getBouquetName(), form.getBouquetDetail(), form.getColor(),form.getPrice(), form.getStock());

        return id;
    }

    @Transactional
    public void deleteBouquet(Long id){
        flowerBouquetRepository.deleteById(id);
    }

    @Transactional
    public void deleteSingle(Long id){
        flowerSingleRepository.deleteById(id);
    }

    /**
     * 부케 리스트 출력 (main page)
     * @return key: 부케, value: 가게
     */
    public Map<FlowerBouquet, Business> findBouquetList(){
        Map<FlowerBouquet, Business> bouquetMap = new HashMap<>();
        List<FlowerBouquet> bouquetList = flowerBouquetRepository.findAll();

        for (FlowerBouquet flowerBouquet : bouquetList) {
            Long businessId = flowerBouquet.getBusiness().getId();
            Business business = businessRepository.findById(businessId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));
            bouquetMap.put(flowerBouquet, business);
        }
        return bouquetMap;
    }

    /**
     * 단품 리스트 출력 (main page)
     * @return key : 단품 꽃,  value : 가게
     */
    public Map<FlowerSingle, Business> findSingleList(){
        Map<FlowerSingle, Business> singleMap = new HashMap<>();
        List<FlowerSingle> singleList = flowerSingleRepository.findAll();

        for (FlowerSingle flowerSingle : singleList) {
            Long businessId = flowerSingle.getBusiness().getId();
            Business business = businessRepository.findById(businessId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));
            singleMap.put(flowerSingle, business);
        }
        return singleMap;
    }
}
