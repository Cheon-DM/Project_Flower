package project.flower.domain.member;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import project.flower.domain.cart.Cart;
import project.flower.domain.favorite.Favorite;
import project.flower.domain.order.FlowerOrder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String sex;

    // FK
    @OneToOne(mappedBy = "memberId")
    private Favorite favoriteId;

    @OneToOne(mappedBy = "memberId")
    private Cart cartId;

    @OneToOne(mappedBy = "memberId")
    private FlowerOrder flowerOrderId;
}
