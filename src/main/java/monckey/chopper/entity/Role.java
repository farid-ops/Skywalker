package monckey.chopper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String rolename;

    @ManyToOne
    @JoinColumn
    private User user;

    public Role(){
        super();
    }

    public Role(String rolename){
        this.rolename = rolename;
    }
}
