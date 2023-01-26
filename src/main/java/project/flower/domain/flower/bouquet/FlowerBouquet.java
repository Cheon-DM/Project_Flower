package project.flower.domain.flower.bouquet;

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
public class FlowerBouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bouquetId;

    @Column(name="bouquet_name")
    private String bouquetName;
    @Column(name="bouquet_detail")
    private String bouquetDetail;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    private String color;
    private int price;
    private int stock;
    private String imageUrl;
}
