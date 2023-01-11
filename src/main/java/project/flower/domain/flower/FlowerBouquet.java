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
public class FlowerBouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bouquetId;

    private String bouquetName;
    private String bouquetDetail;
    private int adminId;
    private String color;
    private int price;
    private int stock;
}
