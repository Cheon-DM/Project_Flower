package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.flower.bouquet.FlowerBouquet;

public interface FlowerBouquetRepository extends JpaRepository<FlowerBouquet, Long> {
}
