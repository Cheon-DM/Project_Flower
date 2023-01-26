package project.flower.domain.admin;

import lombok.Getter;
import project.flower.domain.member.Member;

import java.io.Serializable;

@Getter
public class AdminSessionDto implements Serializable {

    private String email;
    private String name;
    private String password;
    private int age;

    private int businessNum;

    private String businessName;

    // Entity -> Dto
    public AdminSessionDto(Admin admin) {
        this.email = admin.getEmail();
        this.name = admin.getName();
        this.password = admin.getPassword();
        this.age = admin.getAge();
        this.businessNum=admin.getBusinessNum();
        this.businessName=admin.getBusinessName();
    }
}
