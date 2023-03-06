package project.flower.domain.flower.selfmade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerColor;
import project.flower.domain.flower.FlowerType;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FlowerSingleForm {

    private String name;
    private String flowerLang;
    private FlowerColor color;
    private int price;
    private int stock;

    private MultipartFile imgFile;

    private String imgName; //이미지 파일명
    private String imgPath;// 이미지 조회경로

    public FlowerSingle toEntity(Business business){
        return FlowerSingle.builder()
                .name(name)
                .flowerLang(flowerLang)
                .color(color)
                .price(price)
                .stock(stock)
                .imgName(imgName)
                .imgPath(imgPath)
                .type(FlowerType.FLOWER_SINGLE)
                .build();
    }
}
