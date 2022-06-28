package monckey.chopper.service;

import monckey.chopper.entity.Address;
import monckey.chopper.entity.Card;
import monckey.chopper.entity.Customer;
import monckey.chopper.repo.CardRepository;
import monckey.chopper.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public UserServiceImpl(UserRepository userRepository,
                           CardRepository cardRepository){
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void deleteCustomerById(String customerId) {
        this.userRepository.deleteById(UUID.fromString(customerId));
    }

    @Override
    public Optional<Iterable<Address>> getAddressByCustomerId(String id) {
        return this.userRepository.findById(UUID.fromString(id)).map(Customer::getAddresses);
    }

    @Override
    public Iterable<Customer> getAllCustomers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<Card> getCardByCustomerId(String customerId) {
        return Optional.of(this.userRepository.findById(UUID.fromString(customerId)).map(Customer::getCards).get().get(0));
    }

    @Override
    public Optional<Customer> getCustomerById(String customerId) {
        return this.userRepository.findById(UUID.fromString(customerId));
    }

//    @Override
//    public Optional<Customer> save(Customer user) {
//        return Optional.of(this.userRepository.save(user));
//    }
}
