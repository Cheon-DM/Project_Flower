package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.flower.selfmade.SelfFlowerBouquet;

import java.util.Optional;

public interface SelfFlowerBouquetRepository extends JpaRepository<SelfFlowerBouquet, Long> {

    @Override
    Optional<SelfFlowerBouquet> findById(Long id);
}
