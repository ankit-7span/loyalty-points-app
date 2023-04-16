package com.loyalty.service.customer.service;

import com.loyalty.service.customer.entity.Customer;
import com.loyalty.service.customer.model.BaseResponse;
import com.loyalty.service.customer.model.ResponseDTO;

import java.util.Optional;

public interface CustomerService {
    BaseResponse deductLoyaltyPoints(Long customerID, Long loyaltyPoints);

    ResponseDTO getPoints(Customer customer);

    Optional<Customer> getCustomer(Long customerId);

    BaseResponse addPointsToWallet(Long customerID, Long purchaseAmount);
}
