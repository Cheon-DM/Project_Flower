package project.flower.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberDetails;
import project.flower.domain.member.MemberSessionDto;
import project.flower.repository.MemberRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    // email이 DB에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. :" + userEmail));

        session.setAttribute("member", new MemberSessionDto(member));

        // security session에 member 정보 저장
        return new MemberDetails(member);
    }
}
