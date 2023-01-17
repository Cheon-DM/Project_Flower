package project.flower.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.flower.domain.member.MemberForm;
import project.flower.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(MemberForm form){
        form.setPassword(encoder.encode(form.getPassword()));
        return memberRepository.save(form.toEntity()).getId();
    }
//
//    public String createMember(MemberForm form) {
//        Member member = new Member();
//        BeanUtils.copyProperties(form, member);
//        memberRepository.save(member);
//        return "Success";
//    }


}
