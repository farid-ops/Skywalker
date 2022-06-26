package monckey.chopper.api;

import monckey.chopper.entity.Address;
import monckey.chopper.entity.Card;
import monckey.chopper.entity.User;
import monckey.chopper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService){
        this.userService = userService;
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomerById(@PathVariable("customerId") String customerId){
        this.userService.deleteCustomerById(customerId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Customer was deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    @GetMapping(value = "/address/{customerId}")
    public ResponseEntity<Optional<Iterable<Address>>> getAddressByCustomerId(@PathVariable("customerId") String customerId){
        return new ResponseEntity<>(this.userService.getAddressByCustomerId(customerId), HttpStatus.FOUND);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Iterable<User>> getAllCustomers(){
        return new ResponseEntity<>(this.userService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(value = "/card/{customerId}")
    public ResponseEntity<Optional<Card>> getCardByCustomerId(@PathVariable("customerId") String customerId){
        return new ResponseEntity<>(this.userService.getCardByCustomerId(customerId), HttpStatus.FOUND);
    }

    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<Optional<User>> getCustomerById(@PathVariable("customerId") String customerId){
        return new ResponseEntity<>(this.userService.getCustomerById(customerId), HttpStatus.FOUND);
    }

}
