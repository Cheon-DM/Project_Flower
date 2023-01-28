package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.admin.Business;
import project.flower.domain.admin.BusinessForm;
import project.flower.domain.member.Member;
import project.flower.repository.BusinessRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;

    public Long registerBusiness(BusinessForm form, Member member){

        Business business = form.toEntity(member);
        business.setMember(member);

        return businessRepository.save(business).getId();
    }

}
