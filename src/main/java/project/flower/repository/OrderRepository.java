package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.member.Member;
import project.flower.domain.order.FlowerOrder;

import java.util.List;

public interface OrderRepository extends JpaRepository<FlowerOrder, Long> {

    List<FlowerOrder> findAllByMember(Member member);
}
