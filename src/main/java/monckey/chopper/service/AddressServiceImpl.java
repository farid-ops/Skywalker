package monckey.chopper.service;

import monckey.chopper.entity.Address;
import monckey.chopper.error.ResourceNotFoundException;
import monckey.chopper.repo.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository address;

    public AddressServiceImpl(AddressRepository address){
        this.address = address;
    }

    @Override
    public List<Address> getAllAddress() {
        return address.findAll();
    }

    @Override
    public Optional<Address> getOneAddress(String addressId) {
       return this.address.findById(UUID.fromString(addressId));
    }

    @Override
    public Address saveAddress(Address address) {
        return this.address.save(address);
    }

    @Override
    public void deleteAddresse(String addressId) {
        this.address.deleteById(UUID.fromString(addressId));
    }
}
