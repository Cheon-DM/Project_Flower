package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.favorite.Favorite;
import project.flower.domain.member.Member;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByMember(Member member);
}
