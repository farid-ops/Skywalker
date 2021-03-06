package monckey.chopper.repo;

import monckey.chopper.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("select o from OrderEntity o join o.user u where u.id=:id")
    Iterable<OrderEntity> findByCustomerId(@Param("id") String id);
}
