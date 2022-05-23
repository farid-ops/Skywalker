package monckey.chopper.repo;

import monckey.chopper.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query(value = "select i from Item i, Cart c, User u where u.id=:id and c.id=u.id and i.id=c.id", nativeQuery = true)
    Iterable<Item> findByCustomerId(@Param("id") String id);

    @Modifying
    @Query(value = "delete from Item i where i.id in :uuids and Cart.id=:id", nativeQuery = true)
    void deleteCartItemById(@Param("uuids") List<UUID> uuids, @Param("id") String cartId);
}
