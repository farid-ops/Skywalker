package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
/*
* le panier
* un panier appartient a un client et un client est associe a un panier.
* et un panier peut contenir plusieurs items et plusieur item peuvent etre
* associe a plusieurs panier.
* */
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Cart {

    @Id @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "User_ID")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ID_Cart_Item_ID",
            joinColumns = @JoinColumn(name = "Cart_ID"),
            inverseJoinColumns = @JoinColumn(name = "Item_ID"))
    private List<Item> items = Collections.emptyList();

    public Cart(){
        super();
    }
}
