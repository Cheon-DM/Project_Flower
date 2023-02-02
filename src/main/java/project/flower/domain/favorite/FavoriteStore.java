package project.flower.domain.favorite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.flower.domain.admin.Business;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FavoriteStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @ManyToOne()
    @JoinColumn(name = "business_id")
    private Business business;
}
