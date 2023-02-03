package project.flower.domain.flower.bouquet;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;

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

    private int price;
    private int stock;
    private String imageUrl;

    //==연관관계 메서드==//
    public void setBusiness(Business business){
        this.business= business;
        business.getBouquetList().add(this);
    }

    public void update(String bouquetName, String bouquetDetail, FlowerColor color, int price, int stock){
        this.bouquetName=bouquetName;
        this.bouquetDetail=bouquetDetail;
        this.color=color;
        this.price=price;
        this.stock=stock;
    }
}
