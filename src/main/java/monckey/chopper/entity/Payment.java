package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private boolean authorize;
    private String message;

    @OneToOne
    private Orders orders;

    public Payment(){
        super();
    }
}
