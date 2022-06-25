package monckey.chopper.api;

import monckey.chopper.entity.Address;
import monckey.chopper.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address")
public class AddressAPI {

    private final AddressService addressService;

    public AddressAPI(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping(value = "/", name = "address")
    public ResponseEntity<List<Address>> getAllAddress(){
        List<Address> address = this.addressService.getAllAddress();
        return ResponseEntity.ok().body(address);
    }

    @GetMapping(value = "/{id}")
    public Optional<Address> getOneAddress(@PathVariable("id") String addressId){
       return this.addressService.getOneAddress(addressId);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address){
        Address addressSave = this.addressService.saveAddress(address);
        return ResponseEntity.ok().body(addressSave);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") String addressId){
        Map<String, Boolean> response = new HashMap<>();
        this.addressService.deleteAddresse(addressId);
        response.put("Deleted", true);
        return ResponseEntity.ok().body(response);
    }
}
