package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.cart.Cart;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberForm;
import project.flower.repository.CartRepository;
import project.flower.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void joinUser(MemberForm form){
        Cart cart = Cart.builder().build();
        Member member = form.toUserEntity();
        member.setPassword(encoder.encode(form.getPassword()));
        cart.setMember(member);
        member.setCart(cart);

        memberRepository.save(member).getId();
    }

    @Transactional
    public void joinAdmin(MemberForm form){
        Member member = form.toAdminEntity();
        member.setPassword(encoder.encode(form.getPassword()));
        memberRepository.save(member).getId();
    }

    // email 중복 검사
    public void validateDuplicateMember(Member member) {
        log.info("중복 체크 로직 시작");

        boolean check = memberRepository.existsByEmail(member.getEmail());
        if (check) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
    }

    // 회원 비밀번호 변경
    @Transactional
    public void userPasswordUpdate(MemberForm form) {
        // 회원 찾기
        Member member = memberRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        log.info("name = {}, email = {}, password = {}, age = {}, sex = {}",
                member.getName(), member.getEmail(), member.getPassword(), member.getAge(), member.getSex());

        // 수정한 비밀번호 암호화
        String encryptPassword = encoder.encode(form.getPassword());
        member.setPassword(encryptPassword);
        log.info("암호화 성공");

        log.info("aythenticationManager={}", authenticationManager);
        // 세션 등록
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("회원 비밀번호 수정 성공");
    }
}
