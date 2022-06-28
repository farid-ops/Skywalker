package monckey.chopper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/*
* Un user est associe a un panier pas plus.
* */
@Entity
@Table(name = "customer")
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
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

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Card> cards;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Role> roles = new ArrayList<>();

    /*association unidirectionnelle*/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Adresse",
            joinColumns = @JoinColumn(name = "User_ID"),
            inverseJoinColumns = @JoinColumn(name = "Adresse_Id"))
    private List<Address> addresses = Collections.emptyList();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<OrderEntity> orders = Collections.emptyList();

    public Customer(){
        super();
    }
}
