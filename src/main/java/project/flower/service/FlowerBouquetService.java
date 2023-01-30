package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowerBouquetService {

    private final BusinessRepository businessRepository;
    private final FlowerBouquetRepository flowerBouquetRepository;

    public Map<Long, List<FlowerBouquet>> findBouquetList(){
        List<Business> businessList = businessRepository.findAll();
        Map<Long, List<FlowerBouquet>> bouquetMap = new HashMap<>();
        for (Business business : businessList) {
            List<FlowerBouquet> bouquetList = flowerBouquetRepository.findAllByBusiness(business);
            for (FlowerBouquet bouquet : bouquetList) {
                log.info("list={}", bouquet.getBouquetName());
            }
            bouquetMap.put(business.getId(), bouquetList);
        }
        return bouquetMap;
    }
}
