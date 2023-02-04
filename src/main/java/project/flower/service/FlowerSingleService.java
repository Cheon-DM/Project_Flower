package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerSingleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowerSingleService {

    private final BusinessRepository businessRepository;
    private final FlowerSingleRepository flowerSingleRepository;

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
}
