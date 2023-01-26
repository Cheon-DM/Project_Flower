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
public class FlowerSingle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flowerId;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Member admin;

    private String color;

    @Lob
    @Column(name = "flower_lang")
    private String flowerLang;
    private int price;
    private int stock;
    private String imageUrl;
}
