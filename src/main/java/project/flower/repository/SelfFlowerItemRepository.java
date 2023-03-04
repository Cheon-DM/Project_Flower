package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.flower.selfmade.SelfFlowerBouquet;
import project.flower.domain.flower.selfmade.SelfFlowerItem;

import java.util.List;

public interface SelfFlowerItemRepository extends JpaRepository<SelfFlowerItem, Long> {

    List<SelfFlowerItem> findAllBySelfFlowerBouquet(SelfFlowerBouquet selfFlowerBouquet);


}
