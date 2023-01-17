package project.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.flower.domain.member.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원 가입 후 로그인시 사용
    Optional<Member> findByMemberEmail (String memberId);
}
