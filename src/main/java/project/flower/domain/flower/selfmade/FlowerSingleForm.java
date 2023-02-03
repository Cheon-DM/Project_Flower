package project.flower.domain.flower.selfmade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.FlowerType;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FlowerSingleForm {

    private String flowerName;
    private String flowerLang;
    private FlowerColor color;
    private int price;
    private int stock;

    //이미지는 나중에 따로 테이블 생성
    //private String imageUrl;

    public FlowerSingle toEntity(Business business){
        return FlowerSingle.builder()
                .flowerName(flowerName)
                .flowerLang(flowerLang)
                .color(color)
                .price(price)
                .stock(stock)
                .type(FlowerType.FLOWER_SINGLE)
                .build();
    }
}
