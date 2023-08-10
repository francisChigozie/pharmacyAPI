package com.ironhack.pharmacyapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
public class Roles extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int roleId;
    private String roleName;

   @OneToOne(mappedBy = "roles")
    private Person person;

}