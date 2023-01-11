package repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.flower.domain.admin.Admin;
import project.flower.repository.AdminRepository;

public class AdminRepositoryTests{

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void create(){
        Admin admin = new Admin();

        admin.setAge(12);
        admin.setBusinessName("12");
        admin.setBusinessNum(12);
        admin.setEmail("12");
        admin.setName("12");
        admin.setPassword("12");

        Admin newAd = adminRepository.save(admin);
    }
}
