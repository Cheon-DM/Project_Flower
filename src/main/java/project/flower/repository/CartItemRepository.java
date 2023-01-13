package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
