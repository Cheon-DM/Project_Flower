package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.repository.FlowerBouquetRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class FlowerBouquetService {

    private final FlowerBouquetRepository flowerBouquetRepository;

    public Page<FlowerBouquet> flowerBouquetList(Pageable pageable){
        return flowerBouquetRepository.findAll(pageable);
    }

    public Page<FlowerBouquet> bouquetSearchListByName(String search, Pageable pageable){
        return flowerBouquetRepository.findByNameContaining(search, pageable);
    }

    public Page<FlowerBouquet> bouquetSearchListByDetail(String search, Pageable pageable){
        return flowerBouquetRepository.findByBouquetDetailContaining(search, pageable);
    }

    public Page<FlowerBouquet> bouquetSearchListByNameAndColor(String search, FlowerColor color, Pageable pageable){
        return flowerBouquetRepository.findByNameContainingAndColor(search, color, pageable);
    }

    public Page<FlowerBouquet> bouquetSearchListByDetailAndColor(String search, FlowerColor color, Pageable pageable){
        return flowerBouquetRepository.findByBouquetDetailContainingAndColor(search, color, pageable);
    }

}
