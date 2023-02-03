package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.selfmade.FlowerSingle;

import java.util.List;

public interface FlowerSingleRepository extends JpaRepository<FlowerSingle, Long> {

    List<FlowerSingle> findAllByBusiness(Business business);
}
