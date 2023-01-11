package project.flower.domain.flower;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FlowerImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int flowerImageId;
    private String ingName;
    private String imgUrl;
    private int itemId;
}
