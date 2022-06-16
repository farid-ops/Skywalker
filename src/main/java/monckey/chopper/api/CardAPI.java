package monckey.chopper.api;

import monckey.chopper.entity.Card;
import monckey.chopper.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/cards")
public class CardAPI {

    private final CardService cardService;

    public CardAPI(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Iterator<Card>> getAllCard(){
        Iterator<Card> cards = this.cardService.getAllCards();
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Card>> getOneCard(@PathVariable("id") String cardId){
        return ResponseEntity.ok().body(this.cardService.getOneCard(cardId));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Optional<Card>> saveCard(@RequestBody Card card){
        return ResponseEntity.ok().body(this.cardService.saveCard(card));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCard(@PathVariable("id") String cardId){
        Map<String, Boolean> response = new HashMap<>();
        this.cardService.deleteCard(cardId);
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
}
