package project.flower.domain.admin;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;
import project.flower.domain.member.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessForm {

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{1,20}$", message = "업체명은 특수문자를 제외한 1~20자리여야 합니다.")
    @NotBlank(message = "업체명은 필수 입력 값입니다.")
    private String businessName;
    @Pattern(regexp = "^[0-9-_]{10}$", message = "10자리여야 합니다.")
    @NotBlank(message = "사업자 번호은 필수 입력 값입니다.")
    private String businessNum;

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
