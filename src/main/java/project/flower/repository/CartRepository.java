package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.cart.Cart;
import project.flower.domain.member.Member;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByMember(Member member);
}
