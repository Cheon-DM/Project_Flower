package project.flower.domain.flower.bouquet;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.admin.Admin;
import project.flower.domain.flower.NotEnoughStockException;

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

    @Lob
    @Column(name="bouquet_detail")
    private String bouquetDetail;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private String color;
    private int price;
    private int stock;
    private String imageUrl;

    //==비즈니스 로직==//
    public void addStock(int quantity){
        this.stock+=quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stock-quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stock=restStock;
    }
}
