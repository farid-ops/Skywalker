package monckey.chopper.service;

import monckey.chopper.entity.Card;
import monckey.chopper.error.ResourceNotFoundException;
import monckey.chopper.repo.CardRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @Override
    public Iterator<Card> getAllCards() {
        return this.cardRepository.findAll().iterator();
    }

    @Override
    public Optional<Card> getOneCard(String cardId) {
        //.orElseThrow(()-> new ResourceNotFoundException(String.format("Card with this %s ID "+cardId+" not found.")));
        return this.cardRepository.findById(UUID.fromString(cardId));
    }

    @Override
    public Optional<Card> saveCard(Card card) {
        return Optional.of(this.cardRepository.save(card));
    }

    @Override
    public void deleteCard(String cardId) {
        this.cardRepository.deleteById(UUID.fromString(cardId));
    }
}
