package monckey.chopper.service;

import monckey.chopper.entity.Cart;
import monckey.chopper.entity.Item;
import monckey.chopper.error.CustomerNotFoundException;
import monckey.chopper.error.GenericAlreadyExistsException;
import monckey.chopper.error.ItemNotFoundException;
import monckey.chopper.repo.CartRepository;
import monckey.chopper.repo.UserRepository;
import org.springframework.stereotype.Service;
import static org.springframework.objenesis.instantiator.util.UnsafeUtils.getUnsafe;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Item> addCartItemByCustomerId(String customerId, Item item) {

        Cart cart = this.getCartByCustomerId(customerId);
        long count=cart.getItems().stream().filter(i->i.getProduct().getId().equals(item.getId())).count();

        if (count > 0)
            throw new GenericAlreadyExistsException(String.format("Item with (%s) ID already exist, you can update it "+item.getId()));

        cart.getItems().add(item);

        return this.cartRepository.save(cart).getItems();
    }

    @Override
    public List<Item> addOrReplaceItemByCustomerId(String customerId, Item item) {

        Cart optionalCart = this.getCartByCustomerId(customerId);

        List<Item> items = Objects.nonNull(optionalCart.getItems()) ? optionalCart.getItems() : Collections.emptyList();
        AtomicBoolean atomic = new AtomicBoolean(false);

        items.forEach(i->{
            if (i.getProduct().getId().equals(item.getId())){
                i.setPrice(item.getPrice()); i.setQuantity(item.getQuantity()); atomic.set(true);
            }
        });

        items.add(item);

        return this.cartRepository.save(optionalCart).getItems();
    }

    @Override
    public void deleteCart(String customerId) {
        Cart cart = this.getCartByCustomerId(customerId);
        this.cartRepository.delete(cart);
    }

    @Override
    public void deleteItemFromCart(String customerId, String itemId) {
        Cart cart = this.getCartByCustomerId(customerId);
        List<Item> items = cart.getItems().stream().filter(item->!item.getProduct().getId().equals(itemId)).collect(Collectors.toList());
        cart.setItems(items);
        cartRepository.save(cart);
    }

    @Override
    public Cart getCartByCustomerId(String customerId) {
        Cart cart = this.cartRepository.findCartByCustomerId(UUID.fromString(customerId));
        if (Objects.isNull(cart.getCustomer())){
            cart.setCustomer(this.userRepository.findById(UUID.fromString(customerId))
                    .orElseThrow(()-> new CustomerNotFoundException(String.format("-%s "+customerId)))
            );
        }
        return cart;
    }

    @Override
    public Item getCartItemByItemId(String customerId, String itemId) {

        Cart cart = this.cartRepository.findCartByCustomerId(UUID.fromString(customerId));
        AtomicReference<Item> itemAtomicReference = new AtomicReference<>();

        cart.getItems().forEach(item->{
            if (item.getProduct().getId().equals(itemId))
                itemAtomicReference.set(item);
        });

        if (Objects.isNull(itemAtomicReference.get()))
            getUnsafe().throwException(new ItemNotFoundException(String.format(" - %s"+itemId)));
        return null;
    }
}
