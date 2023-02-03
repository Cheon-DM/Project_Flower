package project.flower.domain.flower.bouquet;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.FlowerType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FlowerBouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="bouquet_name")
    private String bouquetName;
    @Column(name="bouquet_detail")
    private String bouquetDetail;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Enumerated(EnumType.STRING)
    private FlowerColor color; // 꽃색깔 여러가지...

    @Enumerated(EnumType.STRING)
    private FlowerType type;

    private int price;
    private int stock;
    private String imageUrl;

    //==연관관계 메서드==//
    public void setBusiness(Business business){
        this.business= business;
        business.getBouquetList().add(this);
    }
}
