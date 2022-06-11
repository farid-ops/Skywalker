package monckey.chopper.service;

import monckey.chopper.entity.Card;

import java.util.Iterator;
import java.util.Optional;

public interface CardService {

    Iterator<Card> getAllCards();

    Optional<Card> getOneCard(String cardId);

    Optional<Card> saveCard(Card card);

    void deleteCard(String cardId);
}
