package com.loyalty.service.customer.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String email;

    private Long number;

    private String address;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "wallet_id",referencedColumnName = "Id")
    private Wallet wallet;
}
