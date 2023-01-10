package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.Admin;
import project.flower.domain.AdminForm;
import project.flower.repository.AdminRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinAdmin {

    private final AdminRepository adminRepository;



    public String  join(AdminForm form){
        adminRepository.save(Admin.builder()
                .name(form.getName())
                .age(form.getAge())
                .email(form.getEmail())
                .password(form.getPassword())
                .businessNum(form.getBusinessNum())
                .businessName(form.getBusinessName()).build());
        return "Sucess";
    }

}
