package project.flower.domain.favorite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FavoriteStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @Column(name = "business_num")
    private int businessNum;

    @Column(name = "business_name")
    private String businessName;
}
