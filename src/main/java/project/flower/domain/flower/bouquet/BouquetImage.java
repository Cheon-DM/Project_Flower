package project.flower.domain.flower.bouquet;


import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "file")
public class BouquetImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bouquet_id")
    private FlowerBouquet bouquet;

    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

/*    @Builder
    public BouquetImage(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }*/

    public void setFlowerBouquet(FlowerBouquet bouquet){
        this.bouquet=bouquet;
        bouquet.getImageList().add(this);
       /* if(!bouquet.getImageList().contains(this)){
          }
        */
    }


}
