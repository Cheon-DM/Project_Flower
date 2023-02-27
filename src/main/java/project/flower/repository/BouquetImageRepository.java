package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.flower.bouquet.BouquetImage;

public interface BouquetImageRepository extends JpaRepository<BouquetImage, Long> {
}
