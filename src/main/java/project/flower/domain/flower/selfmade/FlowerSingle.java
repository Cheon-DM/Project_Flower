package project.flower.domain.flower.selfmade;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Business;
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
    @Column(name = "single_flower_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "flower_name")
    private String flowerName;

    private String color;

    @Lob
    @Column(name = "flower_lang")
    private String flowerLang;
    private int price;
    private int stock;
    private String imageUrl;
}
