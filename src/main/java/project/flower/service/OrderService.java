package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.cart.Cart;
import project.flower.domain.cart.CartItem;
import project.flower.domain.member.Member;
import project.flower.domain.order.FlowerOrder;
import project.flower.domain.order.FlowerOrderItem;
import project.flower.domain.order.OrderStatus;
import project.flower.repository.CartItemRepository;
import project.flower.repository.CartRepository;
import project.flower.repository.OrderItemRepository;
import project.flower.repository.OrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void checkout(Member member, String[] itemIds){

        // 주문(order) 생성
        FlowerOrder order = FlowerOrder.builder()
                .member(member)
                .build();

        orderRepository.save(order);

        for (String id : itemIds) {
            CartItem cartItem = cartItemRepository.findById(Long.valueOf(id))
                    .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 카트에 존재하지 않습니다."));

            // 주문 아이템(orderItem) 관련 항목 생성 -> 후에 인기 많은 상품으로 이용가능
            FlowerOrderItem orderItem = FlowerOrderItem.builder()
                    .name(cartItem.getItemName())
                    .status(OrderStatus.PROCESSING)
                    .count(cartItem.getCount())
                    .price(cartItem.getPrice())
                    .type(cartItem.getType())
                    .flowerId(cartItem.getItemId())
                    .flowerOrder(order)
                    .business(cartItem.getBusiness())
                    .build();

            orderItemRepository.save(orderItem);

            // 장바구니(cart)에 담긴 항목 삭제
            cartItemRepository.delete(cartItem);
        }
    }

    public List<List<FlowerOrderItem>> findOrder(Member member){
        List<List<FlowerOrderItem>> orderList = new ArrayList<>(new ArrayList<>());
        List<FlowerOrder> orders = orderRepository.findAllByMember(member);

        for (FlowerOrder order : orders) {
            List<FlowerOrderItem> flowers = orderItemRepository.findAllByFlowerOrder(order);
            orderList.add(flowers);
        }
        return orderList;
    }
}
