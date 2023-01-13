package project.flower.domain.flower.selfmade;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.SelfFlowerBouquet;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SelfFlowerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "self_item_id")
    private int selfItemId;

    @ManyToOne
    @JoinColumn(name = "self_bouquet_id")
    private SelfFlowerBouquet selfFlowerBouquet;

    @ManyToOne
    @JoinColumn(name = "flower_id")
    private FlowerSingle flowerSingle;

    private int count;
}
