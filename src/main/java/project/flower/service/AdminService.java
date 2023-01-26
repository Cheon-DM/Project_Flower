//package project.flower.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import project.flower.domain.admin.Admin;
//import project.flower.domain.admin.AdminForm;
//import project.flower.repository.AdminRepository;
//
//import javax.swing.text.html.Option;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class AdminService {
//
//    private final AdminRepository adminRepository;
//
//    public String createAdmin(AdminForm form){
//        Admin admin = new Admin();
//        BeanUtils.copyProperties(form, admin);
//        adminRepository.save(admin);
//        return "Success";
//    }
//
//    public String signInAdmin(String email, String password){
//        Optional<Admin> admin = adminRepository.findByEmail(email);
//        log.info("db password = {},input password = {}", admin.get().getPassword(), password);
//        if(admin.get().getPassword().equals(password)){
//            return "Success";
//        }
//        return "Failed";
//    }
//}
