package monckey.chopper.service;

import monckey.chopper.entity.Address;
import monckey.chopper.entity.Card;
import monckey.chopper.entity.Customer;

import java.util.Optional;

public interface UserService {

    void deleteCustomerById(String customerId);

    Optional<Iterable<Address>> getAddressByCustomerId(String id);

    Iterable<Customer> getAllCustomers();

    Optional<Card> getCardByCustomerId(String customerId);

    Optional<Customer> getCustomerById(String customerId);

//    Optional<Customer> save(Customer user);
}
