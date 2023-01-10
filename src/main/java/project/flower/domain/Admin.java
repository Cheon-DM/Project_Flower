package project.flower.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {

    private Long admin_id;
    private String name;
    private int age;
    private String email;
    private String password;
    private int business_num;
    private String business_name;
}
