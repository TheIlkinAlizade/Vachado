package com.vachado.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    private String username;

    private String firstName;

    private String lastName;

    @Column(name="email")
    private String email;

    private Integer points;

    @Column(name="mobile_number")
    private String mobileNumber;

}