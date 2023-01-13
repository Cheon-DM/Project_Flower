package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
