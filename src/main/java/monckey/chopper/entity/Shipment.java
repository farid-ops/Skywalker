package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


@Entity
@AllArgsConstructor
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    private Timestamp estDelivery;

    private String carrier;

    @OneToOne
    private OrderEntity orderEntity;

    public Shipment(){
        super();
    }
}
