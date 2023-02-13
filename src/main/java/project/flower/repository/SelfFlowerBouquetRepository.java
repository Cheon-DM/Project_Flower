package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.flower.selfmade.SelfFlowerBouquet;

public interface SelfFlowerBouquetRepository extends JpaRepository<SelfFlowerBouquet, Long> {
}
