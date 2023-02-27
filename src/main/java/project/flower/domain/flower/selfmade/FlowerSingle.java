package project.flower.domain.flower.selfmade;

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
public class FlowerSingle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "single_flower_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "flower_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private FlowerColor color;

    @Enumerated(EnumType.STRING)
    private FlowerType type;

    @Lob
    @Column(name = "flower_lang")
    private String flowerLang;
    private int price;
    private int stock;
    private String imgName; //이미지 파일명
    private String imgPath;// 이미지 조회경로

    //==연관관계 메서드==//
    public void setBusiness(Business business){
        this.business= business;
        business.getSingleList().add(this);
    }


    public void update(String name, String flowerLang, FlowerColor color, int price, int stock){
        this.name =name;
        this.flowerLang=flowerLang;
        this.color=color;
        this.price=price;
        this.stock=stock;
    }
}
