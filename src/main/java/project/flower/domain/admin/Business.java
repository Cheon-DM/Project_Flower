package project.flower.domain.admin;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.member.Member;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "business_num",nullable = false)
    private int businessNum;

    //FK
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Member admin;

    @OneToMany(mappedBy = "business")
    List<FlowerBouquet> bouquetList = new ArrayList<FlowerBouquet>();

    @OneToMany(mappedBy = "business")
    List<FlowerSingle> singleList = new ArrayList<FlowerSingle>();

}

