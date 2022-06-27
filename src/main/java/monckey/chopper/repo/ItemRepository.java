package monckey.chopper.repo;

import monckey.chopper.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query(value = "select i.* from tijara.cart c, tijara.item i, tijara.customer u, tijara.cart_item ci where u.id = :customerId and c.customer_id=u.id and c.id=ci.cart_id and i.id=ci.item_id", nativeQuery=true)
    Iterable<Item> findByCustomerId(UUID customerId);

    @Modifying
    @Query(value = "delete from tijara.cart_item where item_id in (:ids) and cart_id = :cartId", nativeQuery = true)
    void deleteCartItemById(List<UUID> ids,UUID cartId);
}
