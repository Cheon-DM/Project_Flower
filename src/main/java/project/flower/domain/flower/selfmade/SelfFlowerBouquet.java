package project.flower.domain.flower.selfmade;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Business;
import project.flower.domain.member.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SelfFlowerBouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "self_bouquet_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

   @ManyToOne
   @JoinColumn(name = "member_id")
    private Member member;

   private int totalPrice=0;

   @OneToMany(mappedBy = "selfFlowerBouquet", fetch = FetchType.EAGER)
   List<SelfFlowerItem> selfFlowerItemList = new ArrayList<SelfFlowerItem>();

   public void addPrice(SelfFlowerItem selfFlowerItem){
       int addPrice = selfFlowerItem.getTotalPrice();
       this.totalPrice+=addPrice;
   }


}
