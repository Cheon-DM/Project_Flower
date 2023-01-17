package project.flower.domain.member;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class MemberSessionDto implements Serializable {

    private String email;
    private String name;
    private String password;
    private int age;
    private String sex;

    // Entity -> Dto
    public MemberSessionDto(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.password = member.getPassword();
        this.age = member.getAge();
        this.sex = member.getSex();
    }
}
