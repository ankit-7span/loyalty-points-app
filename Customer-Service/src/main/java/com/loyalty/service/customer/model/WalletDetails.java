package com.loyalty.service.customer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WalletDetails {
    private Long customerId;
    private Long loyaltyPoints;
}
