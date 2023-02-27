package project.flower.domain.flower.bouquet;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BouquetImageDto {

    private String origFileName;  // 파일 원본명

    private String filePath;  // 파일 저장 경로

    private Long fileSize;
}
