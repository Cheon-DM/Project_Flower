package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FlowerBouquetRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class FlowerBouquetService {

    private final BusinessRepository businessRepository;
    private final FlowerBouquetRepository flowerBouquetRepository;


}
