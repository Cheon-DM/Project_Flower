
package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.admin.Business;
import project.flower.domain.favorite.Favorite;
import project.flower.domain.favorite.FavoriteStore;

import java.util.List;
import java.util.Optional;

public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {

    public List<FavoriteStore> findAllByFavorite(Favorite id);

    Optional<FavoriteStore> findByFavoriteAndBusiness(Favorite fId, Business bId);

    FavoriteStore findFavoriteStoreByFavoriteAndBusiness(Favorite fId, Business bId);
}
