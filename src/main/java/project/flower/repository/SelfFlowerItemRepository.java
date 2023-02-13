package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.flower.selfmade.SelfFlowerItem;

public interface SelfFlowerItemRepository extends JpaRepository<SelfFlowerItem, Long> {
}
