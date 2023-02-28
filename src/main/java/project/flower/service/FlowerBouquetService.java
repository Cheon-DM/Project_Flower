package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class FlowerBouquetService {

    private final BusinessRepository businessRepository;
    private final FlowerBouquetRepository flowerBouquetRepository;


    public Page<FlowerBouquet> flowerBouquetList(Pageable pageable){
        return flowerBouquetRepository.findAll(pageable);
    }
}
