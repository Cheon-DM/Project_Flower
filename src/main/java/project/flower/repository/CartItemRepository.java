package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.cart.Cart;
import project.flower.domain.cart.CartItem;
import project.flower.domain.flower.FlowerType;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    boolean existsCartItemByTypeAndItemId(FlowerType type, Long id);
    Optional<CartItem> findCartItemByTypeAndItemId(FlowerType type, Long id);

    List<CartItem> findAllByCart(Cart cart);
}
