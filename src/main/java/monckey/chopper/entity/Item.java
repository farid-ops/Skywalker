package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cart> carts = Collections.emptyList();

    public Item(){
        super();
    }
}
