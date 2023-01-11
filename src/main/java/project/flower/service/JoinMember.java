package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberForm;
import project.flower.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinMember {

    private final MemberRepository memberRepository;

    public String join(MemberForm form) {
        memberRepository.save(Member.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(form.getPassword())
                .age(form.getAge())
                .sex(form.getSex()).build());
        return "Success";
    }
}
