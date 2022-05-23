package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String number;
    private String residence;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<OrderEntity> orders = new ArrayList<>();

    public Address(){
        super();
    }
}
