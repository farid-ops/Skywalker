package monckey.chopper.repo;

import monckey.chopper.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("select c from Card c join c.user u where u.id=:id")
    Cart findCartByCustomerId(@Param("id") UUID id);
}
