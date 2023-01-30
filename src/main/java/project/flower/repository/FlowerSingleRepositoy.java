package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.flower.selfmade.FlowerSingle;

public interface FlowerSingleRepositoy extends JpaRepository<FlowerSingle, Long> {

}
