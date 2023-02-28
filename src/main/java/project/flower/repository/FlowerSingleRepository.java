package project.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.selfmade.FlowerSingle;

import java.util.List;

public interface FlowerSingleRepository extends JpaRepository<FlowerSingle, Long> {

    @Override
    List<FlowerSingle> findAll();

    List<FlowerSingle> findAllByBusiness(Business business);

    Page<FlowerSingle> findByNameContaining(String searchKeyword, Pageable pageable);
    Page<FlowerSingle> findByFlowerLangContaining(String searchKeyword, Pageable pageable);
}
