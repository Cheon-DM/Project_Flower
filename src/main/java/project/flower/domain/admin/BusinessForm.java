package project.flower.domain.admin;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.flower.domain.member.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessForm {

    private String businessName;
    private int businessNum;

    public Business toEntity(Member member){
        return Business.builder()
                .businessName(businessName)
                .businessNum(businessNum)
                .member(member)
                .build();
    }
}
