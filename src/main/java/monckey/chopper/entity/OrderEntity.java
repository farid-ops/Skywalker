package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Address address;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Card card;

    private BigDecimal totale;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Timestamp orderDate;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Payment payment;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Shipment shipment;

    @OneToOne
    private Authorization authorization;

    @ManyToMany
    @JoinTable(name = "Item_order", joinColumns = @JoinColumn, inverseJoinColumns = @JoinColumn)
    private List<Item> items = new ArrayList<>();

    public OrderEntity(){
        super();
    }
}
