package project.flower.domain.admin;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.cart.CartItem;
import project.flower.domain.favorite.FavoriteStore;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.member.Member;
import project.flower.domain.order.FlowerOrder;
import project.flower.domain.order.FlowerOrderItem;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="business_name",nullable = false)
    private String businessName;

    @Column(name = "business_num",nullable = false)
    private int businessNum;

    //FK
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    List<FavoriteStore> favoriteStoreList;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    List<FlowerBouquet> bouquetList = new ArrayList<FlowerBouquet>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    List<FlowerSingle> singleList = new ArrayList<FlowerSingle>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    List<FlowerOrderItem> orderItemList = new ArrayList<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    List<CartItem> cartItemList = new ArrayList<>();

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member=member;
        member.getBusinessList().add(this);
    }

}

