package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.favorite.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
