package project.flower.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.flower.domain.cart.Cart;
import project.flower.domain.cart.CartItem;
import project.flower.domain.flower.FlowerType;
import project.flower.domain.flower.bouquet.FlowerBouquet;
import project.flower.domain.flower.selfmade.FlowerSingle;
import project.flower.domain.flower.selfmade.SelfFlowerBouquet;
import project.flower.domain.member.Member;
import project.flower.repository.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final FlowerBouquetRepository bouquetRepository;
    private final FlowerSingleRepository singleRepository;
    private final SelfFlowerBouquetRepository selfFlowerBouquetRepository;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void saveBouquet(Long bouquetId, Member member) {
        FlowerBouquet bouquet = bouquetRepository.findById(bouquetId)
                .orElseThrow(() -> new IllegalArgumentException("해당 부케는 존재하지 않습니다."));

        Cart cart = getCart(member);

        CartItem item = CartItem.builder()
                .itemName(bouquet.getName())
                .price(bouquet.getPrice())
                .count(1)
                //.imgUrl(bouquet.getImageUrl())
                .cart(cart)
                .type(FlowerType.FLOWER_BOUQUET)
                .itemId(bouquetId)
                .business(bouquet.getBusiness())
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

    public void saveSingle(Long singleId, Member member) {
        FlowerSingle single = singleRepository.findById(singleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 싱글 꽃은 존재하지 않습니다."));

        Cart cart = getCart(member);

        CartItem item = CartItem.builder()
                .itemName(single.getName())
                .price(single.getPrice())
                .count(1)
                //.imgUrl(single.getImageUrl())
                .cart(cart)
                .type(FlowerType.FLOWER_SINGLE)
                .itemId(singleId)
                .business(single.getBusiness())
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

    public void saveDiyBouquet(Long id, Member member){
        SelfFlowerBouquet selfFlowerBouquet = selfFlowerBouquetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 제작 꽃은 존재하지 않습니다."));

        Cart cart=getCart(member);

        CartItem item = CartItem.builder()
                .itemName("DIY 부케"+selfFlowerBouquet.getId())
                .price(selfFlowerBouquet.getTotalPrice())
                .count(1)
                //.imgUrl()
                .cart(cart)
                .type(FlowerType.FLOWER_SELF_BOUQUET)
                .itemId(selfFlowerBouquet.getId())
                .build();

        cartItemRepository.save(item);


    }

    public CartItem findCartItem(Long id){
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카트 아이템이 없습니다."));
    }

    public List<CartItem> findCartItems(Member member){
        // member로 cartId 찾기
        Cart cart = getCart(member);

        log.info("cart id ={}",cart.getId());

        // cart로 cartItems(카드에 담긴 아이템들) 찾기
        return cartItemRepository.findAllByCart(cart);
    }

    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카트 아이템은 존재하지 않습니다."));
        cartItemRepository.delete(cartItem);
    }

    // 카트 아이템 개수 띄우기
    public int showItemCount(Member member){
        Cart cart = cartRepository.findByMember(member)
                .orElseThrow(() -> new IllegalArgumentException("해당 카트가 존재하지 않습니다."));
        List<CartItem> cartItemList = cart.getCartItemList();
        //log.info("cartItemList={}",cartItemList);
        return cartItemList.size();
    }

    public void editCartItem(Long cartItemId){

    }

    private Cart getCart(Member member) {
        return cartRepository.findByMember(member)
                .orElseThrow(() -> new IllegalArgumentException("해당 카트는 존재하지 않습니다."));
    }
}
