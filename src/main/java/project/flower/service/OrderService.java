package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.admin.Business;
import project.flower.domain.cart.CartItem;
import project.flower.domain.flower.FlowerType;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.SelfFlowerBouquet;
import project.flower.domain.flower.selfmade.SelfFlowerItem;
import project.flower.domain.member.Member;
import project.flower.domain.order.FlowerOrder;
import project.flower.domain.order.FlowerOrderItem;
import project.flower.domain.order.OrderStatus;
import project.flower.repository.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final FlowerBouquetRepository flowerBouquetRepository;
    private final FlowerSingleRepository flowerSingleRepository;
    private final SelfFlowerBouquetRepository selfFlowerBouquetRepository;
    private final SelfFlowerItemRepository selfFlowerItemRepository;
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

    public Map<LocalDateTime, List<FlowerOrderItem>> findOrder(Member member){
        Map<LocalDateTime, List<FlowerOrderItem>> orderMap = new HashMap<>();
        List<FlowerOrder> orders = orderRepository.findAllByMember(member);

        for (FlowerOrder order : orders) {
            List<FlowerOrderItem> flowers = orderItemRepository.findAllByFlowerOrder(order);
            orderMap.put(order.getCreateDate(), flowers);
        }
        return orderMap;
    }

    public Map<String, List<FlowerOrderItem>> showOrder_Admin(List<Business> businessList){
        Map<String, List<FlowerOrderItem>> orderMap = new HashMap<>();
        for (Business business : businessList) {
            List<FlowerOrderItem> flowers = orderItemRepository.findAllByBusiness(business);
            orderMap.put(business.getBusinessName(), flowers);
        }

        return orderMap;
    }

    public void inDelivery(String orderId){
        FlowerOrderItem orderItem = orderItemRepository.findById(Long.valueOf(orderId))
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        // 상태 바꾸기
        orderItem.setStatus(OrderStatus.IN_DELIVERY);
        orderItemRepository.save(orderItem);

        // 사장 물품 개수 줄이기
        FlowerType type = orderItem.getType();

        if (type.equals(FlowerType.FLOWER_BOUQUET)){
            FlowerBouquet flowerBouquet = flowerBouquetRepository.findById(orderItem.getFlowerId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 아이템 존재하지 않습니다."));
            flowerBouquet.setStock(orderItem.getCount());
            flowerBouquetRepository.save(flowerBouquet);
        } else if (type.equals(FlowerType.FLOWER_SINGLE)){
            FlowerSingle flowerSingle = flowerSingleRepository.findById(orderItem.getFlowerId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 아이템 존재하지 않습니다."));
            flowerSingle.setStock(orderItem.getCount());
            flowerSingleRepository.save(flowerSingle);
        } else if (type.equals(FlowerType.FLOWER_SELF_BOUQUET)){
            SelfFlowerBouquet selfFlowerBouquet = selfFlowerBouquetRepository.findById(orderItem.getFlowerId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 아이템 존재하지 않습니다."));
            List<SelfFlowerItem> selfFlowerItems = selfFlowerItemRepository.findAllBySelfFlowerBouquet(selfFlowerBouquet);
            for (SelfFlowerItem selfFlowerItem : selfFlowerItems) {
                FlowerSingle flowerSingle = flowerSingleRepository.findById(selfFlowerItem.getFlowerSingle().getId())
                        .orElseThrow(() -> new IllegalArgumentException("아이템 존재하지 않음"));
                flowerSingle.setStock(orderItem.getCount());
                flowerSingleRepository.save(flowerSingle);
            }
        }

    }

    public void complete(String orderId){
        FlowerOrderItem orderItem = orderItemRepository.findById(Long.valueOf(orderId))
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        // 상태 바꾸기
        orderItem.setStatus(OrderStatus.DELIVERY_COMPLETE);
        orderItemRepository.save(orderItem);
    }
}
