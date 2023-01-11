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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long favoriteStoreId;

    @ManyToOne
    @JoinColumn(name = "favorite_id")
    private Favorite favoriteId;

    private int businessNumber;
}
