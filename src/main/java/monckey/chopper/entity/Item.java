package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/*
* plusieurs objets peuvent etre associe a plusieur panier
* */
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Product product;

    private BigDecimal price;
    private Integer quantity;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cart> carts = Collections.emptyList();

    @ManyToMany
    private List<OrderEntity> orders = new ArrayList<>();

    public Item(){
        super();
    }
}
