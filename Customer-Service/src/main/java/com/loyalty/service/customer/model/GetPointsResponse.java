package com.loyalty.service.customer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPointsResponse {
    private Long customerId;
    private String customerName;
    private Long walletBalance;
}
