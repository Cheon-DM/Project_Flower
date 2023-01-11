package project.flower.domain.favorite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.flower.domain.member.Member;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

    // FK
    @OneToMany (mappedBy = "favorite_id")
    private List<FavoriteStore> favorites;
}
