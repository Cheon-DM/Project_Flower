package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.flower.domain.admin.Business;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, Long> {

    public List<Business> findAll();

    @Override
    Optional<Business> findById(Long id);
}
