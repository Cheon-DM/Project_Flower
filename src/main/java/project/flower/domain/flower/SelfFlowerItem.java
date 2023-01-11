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
public class SelfFlowerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int selfItemId;
    private int selfFlowerBouquetId;
    private int flowerId;
    private int count;
}
