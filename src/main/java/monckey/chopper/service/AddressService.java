package monckey.chopper.service;

import monckey.chopper.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> getAllAddress();

    Optional<Address> getOneAddress(String addressId);

    Address saveAddress(Address address);

    void deleteAddresse(String addresseId);
}
