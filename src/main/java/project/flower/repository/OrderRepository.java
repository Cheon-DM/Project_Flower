package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.order.FlowerOrder;

public interface OrderRepository extends JpaRepository<FlowerOrder, Long> {
}
