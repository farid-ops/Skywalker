package monckey.chopper.service;

import monckey.chopper.entity.Cart;
import monckey.chopper.entity.Item;

import javax.validation.Valid;
import java.util.List;

public interface CartService {

    List<Item> addCartItemByCustomerId(String customerId, @Valid Item item);

    List<Item> addOrReplaceItemByCustomerId(String customerId, @Valid Item item);

    void deleteCart(String customerId);

    void deleteItemFromCart(String customerId, String itemId);

    Cart getCartByCustomerId(String customerId);

    Item getCartItemByItemId(String customerId, String itemId);
}
