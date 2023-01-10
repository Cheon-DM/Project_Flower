package project.flower.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String email;
    private String name;
    private String password;
    private int age;
    private String sex;
    private Long saving;
    private String coupon;
}
