package monckey.chopper.service;

import monckey.chopper.entity.Address;
import monckey.chopper.entity.Card;
import monckey.chopper.entity.User;
import monckey.chopper.repo.CardRepository;
import monckey.chopper.repo.UserRepository;

import java.util.Optional;
import java.util.UUID;

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
        return this.userRepository.findById(UUID.fromString(id)).map(User::getAddresses);
    }

    @Override
    public Iterable<User> getAllCustomers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<Card> getCardByCustomerId(String customerId) {
        return cardRepository.findCardByCustomerId(UUID.fromString(customerId));
    }

    @Override
    public Optional<User> getCustomerById(String customerId) {
        return this.userRepository.findById(UUID.fromString(customerId));
    }
}
