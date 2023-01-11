package project.flower.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

    private String name;
    private String email;
    private String password;
    private int age;
    private String sex;
}
