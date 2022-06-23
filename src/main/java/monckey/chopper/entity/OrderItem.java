package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID orderId;
    private UUID itemId;

    public OrderItem(){
        super();
    }

    public OrderItem setOrderId(UUID orderId){
        this.orderId = orderId;
        return this;
    }

    public OrderItem setItemId(UUID itemId){
        this.itemId = itemId;
        return this;
    }
}
