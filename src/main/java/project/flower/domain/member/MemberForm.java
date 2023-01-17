package project.flower.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberForm {

    private String name;
    private String email;
    private String password;
    private int age;
    private String sex;

    // Form(DTO) -> Entity
    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .age(age)
                .sex(sex)
                .build();
    }
}
