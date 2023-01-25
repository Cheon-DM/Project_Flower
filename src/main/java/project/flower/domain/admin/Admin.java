package project.flower.domain.admin;

import jakarta.persistence.*;
import lombok.*;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.selfmade.FlowerSingle;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "business_num",nullable = false, unique = true)
    private int businessNum;

    @Column(name="business_name",nullable = false)
    private String businessName;

    @OneToMany(mappedBy = "admin")
    List<FlowerBouquet> bouquetList = new ArrayList<FlowerBouquet>();

    @OneToMany(mappedBy = "admin")
    List<FlowerSingle> singleList = new ArrayList<FlowerSingle>();
}
