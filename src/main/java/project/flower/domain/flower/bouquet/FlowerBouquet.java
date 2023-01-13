package project.flower.domain.flower.bouquet;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Admin;

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
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private String color;
    private int price;
    private int stock;
    private String imageUrl;
}
