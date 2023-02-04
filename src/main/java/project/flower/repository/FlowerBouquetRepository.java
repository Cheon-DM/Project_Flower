package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.bouquet.FlowerBouquet;

import java.util.List;
import java.util.Optional;

public interface FlowerBouquetRepository extends JpaRepository<FlowerBouquet, Long> {

    @Override
    List<FlowerBouquet> findAll();

    List<FlowerBouquet> findAllByBusiness(Business business);

    Optional<FlowerBouquet> findById(Long id);
}
