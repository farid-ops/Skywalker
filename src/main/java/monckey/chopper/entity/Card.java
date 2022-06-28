package monckey.chopper.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    private String number;
    private String expires;
    private String cvv;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();

    public Card(){
        super();
    }
}
