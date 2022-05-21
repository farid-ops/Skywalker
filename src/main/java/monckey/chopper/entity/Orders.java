package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Address address;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Card card;


    @Enumerated(EnumType.STRING)
    private Status status;

    private Timestamp orderDate;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Payment payment;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Shipment shipment;

    @ManyToMany
    @JoinTable(name = "Item_order", joinColumns = @JoinColumn, inverseJoinColumns = @JoinColumn)
    private List<Item> items = new ArrayList<>();

    public Orders(){
        super();
    }
}
