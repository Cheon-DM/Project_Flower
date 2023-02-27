package project.flower.domain.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.flower.domain.admin.Business;
import project.flower.domain.flower.FlowerType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String itemName;

    @Column
    private int price;

    @Column
    private int count;

    @Column
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    private FlowerType type;

    @Column
    private long itemId;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "BUSINESS_ID")
    private Business business;

    public void plusPrice(int addPrice) {
        this.price += addPrice;
    }

    public void plusCount() {
        this.count += 1;
    }
}
