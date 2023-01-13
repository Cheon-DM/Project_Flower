package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.order.FlowerOrderItem;

public interface OrderItemRepository extends JpaRepository<FlowerOrderItem, Long> {
}
