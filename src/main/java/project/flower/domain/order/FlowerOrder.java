package project.flower.domain.order;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.BaseTimeEntity;
import project.flower.domain.member.Member;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FlowerOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // FK
    @OneToMany(mappedBy = "flowerOrder")
    private List<FlowerOrderItem> orderItems;
}
