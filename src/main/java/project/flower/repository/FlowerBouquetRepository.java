package project.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.bouquet.FlowerBouquet;

import java.util.List;
import java.util.Optional;

public interface FlowerBouquetRepository extends JpaRepository<FlowerBouquet, Long> {

    @Override
    List<FlowerBouquet> findAll();

    List<FlowerBouquet> findAllByBusiness(Business business);

    Optional<FlowerBouquet> findById(Long id);

    Page<FlowerBouquet> findByNameContaining(String searchKeyword, Pageable pageable);
    Page<FlowerBouquet> findByBouquetDetailContaining(String searchKeyword, Pageable pageable);

    Page<FlowerBouquet> findByNameContainingAndColor(String searchKeyword, FlowerColor color, Pageable pageable);
    Page<FlowerBouquet> findByBouquetDetailContainingAndColor(String searchKeyword, FlowerColor color, Pageable pageable);

    Page<FlowerBouquet> findByColor(FlowerColor color, Pageable pageable);
}
