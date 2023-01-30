package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.admin.Business;
import project.flower.domain.favorite.Favorite;
import project.flower.domain.favorite.FavoriteStore;
import project.flower.domain.member.Member;
import project.flower.repository.BusinessRepository;
import project.flower.repository.FavoriteRepository;
import project.flower.repository.FavoriteStoreRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final BusinessRepository businessRepository;
    private final FavoriteStoreRepository favoriteStoreRepository;
    private final FavoriteRepository favoriteRepository;

    public void saveStore(Long businessId, Member user){
        Business b = businessRepository.findById(businessId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게는 존재하지 않습니다."));

        log.info("name={}, business_num={}, admin_id={}", b.getBusinessName(), b.getBusinessNum(), b.getMember());

        Favorite f = favoriteRepository.findByMember(user)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));

        log.info("member_id={}", f.getMember());

        FavoriteStore store = FavoriteStore.builder()
                .businessName(b.getBusinessName())
                .businessNum(b.getBusinessNum())
                .favorite(f)
                .build();

        favoriteStoreRepository.save(store);
    }

    public List<FavoriteStore> findFavoriteStoreAll(Member member){
        // memberId로 favoriteId 찾기
        Favorite favorite = favoriteRepository.findByMember(member)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        // favoriteId로 favortieStore(즐겨찾기 목록) 찾기
        return favoriteStoreRepository.findAllByFavorite(favorite);
    }
}
