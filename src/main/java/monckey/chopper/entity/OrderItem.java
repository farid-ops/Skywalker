package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
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
