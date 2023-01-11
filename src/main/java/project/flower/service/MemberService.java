package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import project.flower.domain.member.Member;
import project.flower.domain.member.MemberForm;
import project.flower.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String createMember(MemberForm form) {
        Member member = new Member();
        BeanUtils.copyProperties(form, member);
        memberRepository.save(member);
        return "Success";
    }
}
