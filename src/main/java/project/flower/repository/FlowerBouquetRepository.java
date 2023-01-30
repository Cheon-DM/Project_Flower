package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.bouquet.FlowerBouquet;

import java.util.List;

public interface FlowerBouquetRepository extends JpaRepository<FlowerBouquet, Long> {

    List<FlowerBouquet> findAllByBusiness(Business business);
}
