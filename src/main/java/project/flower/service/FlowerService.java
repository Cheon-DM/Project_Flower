package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.bouquet.FlowerBouquetForm;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.FlowerSingleForm;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;
import project.flower.repository.FlowerSingleRepository;


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
}
