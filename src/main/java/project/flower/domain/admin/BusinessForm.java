package project.flower.domain.admin;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import project.flower.domain.member.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessForm {

    private String businessName;
    private int businessNum;

    private MultipartFile imgFile;

    private String imgName; //이미지 파일명
    private String imgPath;// 이미지 조회경로

    public Business toEntity(Member member){
        return Business.builder()
                .businessName(businessName)
                .businessNum(businessNum)
                .imgName(imgName)
                .imgPath(imgPath)
                .member(member)
                .build();
    }
}
