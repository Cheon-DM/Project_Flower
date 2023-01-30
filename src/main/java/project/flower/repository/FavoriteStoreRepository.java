
package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.favorite.Favorite;
import project.flower.domain.favorite.FavoriteStore;

import java.util.List;

public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {

    public List<FavoriteStore> findAllByFavorite(Favorite id);
}
