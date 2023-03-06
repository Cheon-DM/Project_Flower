package project.flower.domain.flower.bouquet;

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
public class FlowerBouquetForm {

    private String name;
    private String bouquetDetail;
    private FlowerColor color;

    private int price;
    private int stock;

    private MultipartFile imgFile;

    private String imgName; //이미지 파일명
    private String imgPath;// 이미지 조회경로

    public FlowerBouquet toEntity(Business business){
        return FlowerBouquet.builder()
                .name(name)
                .bouquetDetail(bouquetDetail)
                .color(color)
                .price(price)
                .stock(stock)
                .imgName(imgName)
                .imgPath(imgPath)
                .type(FlowerType.FLOWER_BOUQUET)
                .build();
    }
}
