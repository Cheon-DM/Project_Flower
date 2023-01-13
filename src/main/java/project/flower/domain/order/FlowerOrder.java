package project.flower.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.flower.domain.member.Member;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FlowerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

    // FK
    @OneToMany(mappedBy = "flowerOrderId")
    private List<FlowerOrderItem> orders;
}
