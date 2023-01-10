package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> { //JpaRepository=> findById, findAll사용가능
    //Admin save(Admin admin);
    Optional<Admin> findById(Long id);
}
