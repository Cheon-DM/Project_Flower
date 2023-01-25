package project.flower.service;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import project.flower.domain.admin.Admin;
import project.flower.domain.admin.AdminDetails;
import project.flower.domain.admin.AdminSessionDto;
import project.flower.repository.AdminRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class AdminDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final HttpSession session;

    // email이 DB에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. :" + userEmail));
        log.info("작동중입니다.");
        session.setAttribute("admin", new AdminSessionDto(admin));

        // security session에 member 정보 저장
        return new AdminDetails(admin);
    }
}