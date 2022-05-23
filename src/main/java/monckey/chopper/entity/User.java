package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/*
* Un user est associe a un panier pas plus.
* */
@Entity
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String firstname;
    private String lastname;

    @NotNull(message = "user name is required.")
    @Basic(optional = false)
    private String username;

    private String email;
    private String password;
    private String phone;
    private String status;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private Cart cart;

    @OneToOne
    private Card card;

    /*association unidirectionnelle*/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Adresse",
            joinColumns = @JoinColumn(name = "User_ID"),
            inverseJoinColumns = @JoinColumn(name = "Adresse_Id"))
    private List<Address> addresses = Collections.emptyList();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderEntity> orders = Collections.emptyList();

    public User(){
        super();
    }
}
