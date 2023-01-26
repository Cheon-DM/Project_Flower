package project.flower.domain.flower.selfmade;

import jakarta.persistence.*;
import lombok.*;
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
    private Member admin;

   @ManyToOne
   @JoinColumn(name = "member_id")
    private Member member;

}
