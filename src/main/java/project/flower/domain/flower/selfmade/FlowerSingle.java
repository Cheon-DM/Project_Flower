package project.flower.domain.flower.selfmade;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FlowerSingle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "single_flower_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "flower_name")
    private String flowerName;

    @Enumerated(EnumType.STRING)
    private FlowerColor color;

    @Lob
    @Column(name = "flower_lang")
    private String flowerLang;
    private int price;
    private int stock;
    private String imageUrl;

    //==연관관계 메서드==//
    public void setBusiness(Business business){
        this.business= business;
        business.getSingleList().add(this);
    }

    //====//
    public void update(String flowerName, String flowerLang, FlowerColor color, int price, int stock){
        this.flowerName=flowerName;
        this.flowerLang=flowerLang;
        this.color=color;
        this.price=price;
        this.stock=stock;
    }
}
