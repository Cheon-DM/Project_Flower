package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.admin.Admin;
import project.flower.domain.admin.AdminForm;
import project.flower.domain.cart.Cart;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberForm;
import project.flower.repository.AdminRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void join(AdminForm form){
        Admin admin = form.toEntity();
        admin.setPassword(encoder.encode(form.getPassword()));
        adminRepository.save(admin).getId();
    }
    public String signInAdmin(String email, String password){
        Optional<Admin> admin = adminRepository.findByEmail(email);
        log.info("db password = {},input password = {}", admin.get().getPassword(), password);
        if(admin.get().getPassword().equals(password)){
            return "Success";
        }
        return "Failed";
    }
}
