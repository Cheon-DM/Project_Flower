package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.flower.domain.admin.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> { //JpaRepository=> save, findById, findAll사용가능

    Optional<Admin> findByEmail(String email);
}
