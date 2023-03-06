package project.flower.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FlowerOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // 주문 상태 : 결제완료, 배송준비중, 배송중, 배송완료
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    private FlowerType type;

    @Column(nullable = false)
    private Long flowerId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private FlowerOrder flowerOrder;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
}
