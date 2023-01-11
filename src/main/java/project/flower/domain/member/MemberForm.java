package project.flower.domain.member;

import lombok.Data;

@Data
public class MemberForm {

    private String name;
    private String email;
    private String password;
    private int age;
    private String sex;
}
