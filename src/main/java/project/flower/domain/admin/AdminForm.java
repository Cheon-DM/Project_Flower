package project.flower.domain.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminForm {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "이름은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String name;

    @Range(min = 0, max = 200, message = "나이의 값이 올바르지 않습니다.")
    @NotNull(message = "나이는 필수 입력 값입니다.")
    private int age;


    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    /*@NotBlank(message = "사업자등록번호는 필수 입력값입니다.")
    @Pattern(regexp = "^[0-9]{10}$" , message = "사업자등록번호는 -를 제외한 10자리 숫자만 써주시길 바랍니다.")*/
    private int businessNum;

    @NotBlank(message = "업체명은 필수 입력값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "업체명은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String businessName;

    /*암호회된 패스워드*/
    public void encryptPassword(String BCryptpassword){
        this.password = BCryptpassword;
    }

    /* DTO -> Entity */
    public Admin toEntity() {
        return Admin.builder()
                .name(name)
                .email(email)
                .password(password)
                .age(age)
                .businessName(businessName)
                .businessNum(businessNum)
                .build();
    }

}
