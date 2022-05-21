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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Timestamp estDelivery;

    private String carrier;

    @OneToOne
    private Orders orders;

    public Shipment(){
        super();
    }
}
