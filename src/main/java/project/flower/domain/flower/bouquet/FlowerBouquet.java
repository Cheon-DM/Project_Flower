package project.flower.domain.flower.bouquet;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.FlowerType;

import java.util.ArrayList;
import java.util.List;

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
    private String name;
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
    private String imgName; //이미지 파일명
    private String imgPath;// 이미지 조회경로

    @OneToMany(mappedBy = "bouquet", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<BouquetImage> imageList = new ArrayList<BouquetImage>();

    //==연관관계 메서드==//
    public void setBusiness(Business business){
        this.business= business;
        business.getBouquetList().add(this);
    }

    public void setStock(int diff) {
        this.stock -= diff;
    }

    public void update(String name, String bouquetDetail, FlowerColor color, int price, int stock){
        this.name =name;
        this.bouquetDetail=bouquetDetail;
        this.color=color;
        this.price=price;
        this.stock=stock;
    }

    //부케에서의 파일 처리..?
    public void addImage(BouquetImage image){

        this.imageList.add(image);

        if(image.getBouquet() != this){
            image.setFlowerBouquet(this);
        }
    }
}
