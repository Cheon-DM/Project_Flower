package project.flower.domain.flower.selfmade;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SelfFlowerItemForm {

    private  int count;

    public SelfFlowerItem toEntity(SelfFlowerBouquet bouquet, FlowerSingle single){
        return SelfFlowerItem.builder()
                .selfFlowerBouquet(bouquet)
                .flowerSingle(single)
                .count(count)
                .build();
    }
}
