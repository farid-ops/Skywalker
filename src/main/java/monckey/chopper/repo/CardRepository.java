package monckey.chopper.repo;

import monckey.chopper.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    @Query("select c from Card c where c.customer.id =:customerId")
    Optional<Card> findCardByCustomerId(@Param("customerId") UUID customerId);
}
