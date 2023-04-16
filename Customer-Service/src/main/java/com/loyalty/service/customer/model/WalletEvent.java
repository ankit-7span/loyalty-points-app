package com.loyalty.service.customer.model;

import com.loyalty.service.customer.model.WalletDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletEvent {
    private String message;
    private String status;
    private WalletDetails walletDetails;
}