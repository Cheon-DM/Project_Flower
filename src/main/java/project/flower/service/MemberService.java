package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class MemberService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void join(MemberForm form){
        Cart cart = Cart.builder().build();
        Member member = form.toEntity();
        member.setPassword(encoder.encode(form.getPassword()));
        cart.setMember(member);
        member.setCart(cart);
        memberRepository.save(member).getId();
    }
}
