package project.flower.domain;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS");

    String role;

    Role(String role) {
        this.role = role;
    }
}
