
package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.favorite.FavoriteStore;

public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {
}
