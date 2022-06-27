package monckey.chopper.service;

import monckey.chopper.entity.Address;
import monckey.chopper.entity.Card;
import monckey.chopper.entity.User;

import java.util.Optional;

public interface UserService {

    void deleteCustomerById(String customerId);

    Optional<Iterable<Address>> getAddressByCustomerId(String id);

    Iterable<User> getAllCustomers();

    Optional<Card> getCardByCustomerId(String customerId);

    Optional<User> getCustomerById(String customerId);

//    Optional<User> save(User user);
}
