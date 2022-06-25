package monckey.chopper.api;

import monckey.chopper.entity.Cart;
import monckey.chopper.entity.Item;
import monckey.chopper.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/carts")
public class CartApi {

    private static final Logger log = LoggerFactory.getLogger(CartApi.class);

    private final CartService cartService;

    public CartApi(CartService cartService)
    {
        this.cartService = cartService;
    }

    @PostMapping(value = "/add/{customerId}")
    public ResponseEntity<List<Item>> addCartItemByCustomerId(@PathVariable("customerId") String customerId, @RequestBody @Valid Item item){
        log.info("Request for customer ID: {}\nItem ID : {}", customerId, item);
        return ResponseEntity.ok().body(this.cartService.addCartItemByCustomerId(customerId, item));
    }

    @PostMapping(value = "/replace/{customerId}")
    public ResponseEntity<List<Item>> addOrReplaceItemByCustomerId(@PathVariable("customerId") String customerId, @RequestBody @Valid Item item){
        log.info("");
        return ResponseEntity.ok().body(this.cartService.addOrReplaceItemByCustomerId(customerId, item));
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Map<String, Boolean>> deleteCart(@PathVariable("customerId") String customerId){
        this.cartService.deleteCart(customerId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Cart was deleted", Boolean.TRUE);
        log.info("Cart deleted successfull.");
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    @DeleteMapping(value = "/{customerId}/{itemId}")
    public ResponseEntity<Map<String, Boolean>> deleteItemFromCart(@PathVariable("customerId") String customerId,
                                                                   @PathVariable("itemId") String itemId){
        this.cartService.deleteItemFromCart(customerId, itemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Item from cart was deleted", Boolean.TRUE);
//        log.info("Cart deleted successfull.");
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<Cart> getCartByCustomerId(@PathVariable("customerId") String customerId){
        Cart cart = this.cartService.getCartByCustomerId(customerId);
        return new ResponseEntity<>(cart, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{customerId}/{itemId}")
    public ResponseEntity<Item> getCartItemByItemId(@PathVariable("customerId") String customerId,
                                                    @PathVariable("itemId") String itemId){
        return new ResponseEntity<>(this.cartService.getCartItemByItemId(customerId, itemId), HttpStatus.FOUND);
    }
}
