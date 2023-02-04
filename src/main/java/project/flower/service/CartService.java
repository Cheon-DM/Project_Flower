package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.cart.Cart;
import project.flower.domain.cart.CartItem;
import project.flower.domain.flower.FlowerType;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.member.Member;
import project.flower.repository.CartItemRepository;
import project.flower.repository.CartRepository;
import project.flower.repository.FlowerBouquetRepository;
import project.flower.repository.FlowerSingleRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final FlowerBouquetRepository bouquetRepository;
    private final FlowerSingleRepository singleRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void saveBouquet(Long bouquetId, Member member) {
        FlowerBouquet bouquet = bouquetRepository.findById(bouquetId)
                .orElseThrow(() -> new IllegalArgumentException("해당 부케는 존재하지 않습니다."));

        Cart cart = getCart(member, "해당 유저의 카트가 존재하지 않습니다.");

        CartItem item = CartItem.builder()
                .itemName(bouquet.getBouquetName())
                .price(bouquet.getPrice())
                .count(1)
                .imgUrl(bouquet.getImageUrl())
                .cart(cart)
                .type(FlowerType.FLOWER_BOUQUET)
                .itemId(bouquetId)
                .build();

        // 이미 카트에 담김
        if (cartItemRepository.existsCartItemByTypeAndItemId(FlowerType.FLOWER_BOUQUET, bouquetId)){
            CartItem findItem = cartItemRepository.findCartItemByTypeAndItemId(bouquet.getType(), bouquetId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 카트에 없습니다."));

            findItem.plusCount();
            findItem.plusPrice(bouquet.getPrice());
            cartItemRepository.save(findItem);
        } else { // 카트에 없음.
            cartItemRepository.save(item);
        }
    }

    private Cart getCart(Member member, String s) {
        return cartRepository.findByMember(member)
                .orElseThrow(() -> new IllegalArgumentException(s));
    }

    public void saveSingle(Long singleId, Member member) {
        FlowerSingle single = singleRepository.findById(singleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 싱글 꽃은 존재하지 않습니다."));

        Cart cart = getCart(member, "해당 유저의 카트가 존재하지 않습니다.");

        CartItem item = CartItem.builder()
                .itemName(single.getFlowerName())
                .price(single.getPrice())
                .count(1)
                .imgUrl(single.getImageUrl())
                .cart(cart)
                .type(FlowerType.FLOWER_SINGLE)
                .itemId(singleId)
                .build();

        // 이미 카트에 담김
        if (cartItemRepository.existsCartItemByTypeAndItemId(FlowerType.FLOWER_SINGLE, singleId)){
            CartItem findItem = cartItemRepository.findCartItemByTypeAndItemId(single.getType(), singleId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 카트에 없습니다."));

            findItem.plusCount();
            findItem.plusPrice(single.getPrice());
            cartItemRepository.save(findItem);
        } else { // 카트에 없음.
            cartItemRepository.save(item);
        }
    }

    public List<CartItem> findCartItems(Member member){
        // member로 cartId 찾기
        Cart cart = getCart(member, "해당 카드 아이디가 존재하지 않습니다.");

        log.info("cart id ={}",cart.getId());

        // cart로 cartItems(카드에 담긴 아이템들) 찾기
        return cartItemRepository.findAllByCart(cart);
    }
}
