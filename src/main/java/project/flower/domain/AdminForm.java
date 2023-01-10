package project.flower.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminForm {

    private String name;
    private int age;
    private String email;
    private String password;
    private int businessNum;
    private String businessName;
}
