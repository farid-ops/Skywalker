package monckey.chopper.service;

import monckey.chopper.entity.Address;
import monckey.chopper.entity.Card;
import monckey.chopper.entity.User;

import java.util.Optional;

public interface UserService {

    void deleteCustomerById(String customerId);

    Optional<Address> getCustomerByAddressId(String addressId);

    Iterable<User> getAllCustomers();

    Optional<Card> getCardByCustomerId(String customerId);

    Optional<User> getCustomerById(String customerId);
}
