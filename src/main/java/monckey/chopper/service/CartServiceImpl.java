package monckey.chopper.service;

import monckey.chopper.entity.Cart;
import monckey.chopper.entity.Item;
import monckey.chopper.repo.CartRepository;
import monckey.chopper.repo.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartServiceImpl(CartRepository cartRepository, ItemRepository itemRepository){
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> addCartItemByCustomerId(String customerId, Item item) {
        Optional<Cart> cart = this.getCartByCustomerId(customerId);
        long count = cart.get().getItems().stream().filter(i->i.getProduct().getId().equals(item.getId())).count();
        return null;
    }

    @Override
    public List<Item> addOrReplaceItemByCustomerId(String customerId, Item item) {
        return null;
    }

    @Override
    public void deleteCart(String customerId) {

    }

    @Override
    public void deleteItemFromCart(String customerId, String itemId) {

    }

    @Override
    public Optional<Cart> getCartByCustomerId(String customerId) {
        return this.cartRepository.findCartByCustomerId(UUID.fromString(customerId));
    }

    @Override
    public Item getCartItemByItemId(String customerId, String itemId) {
        return null;
    }
}
