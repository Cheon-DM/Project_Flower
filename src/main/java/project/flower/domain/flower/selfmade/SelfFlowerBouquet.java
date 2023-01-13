package project.flower.domain.flower.selfmade;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Admin;
import project.flower.domain.member.Member;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SelfFlowerBouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int selfBouquetId;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

   @ManyToOne
   @JoinColumn(name = "member_id")
    private Member member;

}
