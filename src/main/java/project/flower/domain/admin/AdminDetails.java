package project.flower.domain.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.flower.domain.member.Member;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class AdminDetails implements UserDetails {

    private final Admin admin;

    // 유저의 권한 목록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(() -> admin.getName());
        return collectors;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getEmail();
    }

    /**
     * 계정 만료 여부
     * true : 만료 X
     * false : 만료 O
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호 만료 여부
     * true : 만료 X
     * false : 만료 O
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자 활성화 여부
     * true : 만료 X
     * false : 만료 O
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
