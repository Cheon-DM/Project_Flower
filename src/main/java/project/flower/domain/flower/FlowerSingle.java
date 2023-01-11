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
public class FlowerSingle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int flowerId;
    private int adminId;
    private String color;
    private String flowerLang;
    private int price;
    private int stock;
}
