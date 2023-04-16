package com.loyalty.service.customer.handler;

import com.loyalty.service.customer.entity.Customer;
import com.loyalty.service.customer.model.BaseResponse;
import com.loyalty.service.customer.model.ResponseDTO;
import com.loyalty.service.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerHandler {

    @Autowired
    CustomerService customerService;

    public BaseResponse addPointsToWallet(Long customerID, Long loyaltyPoint) {
        return customerService.addPointsToWallet(customerID, loyaltyPoint);
    }

    public ResponseDTO getPoints(Long customerID) {
        log.info("Fetching wallet balance having customer id {}", customerID);

        Optional<Customer> customer = customerService.getCustomer(customerID);
        log.debug("Fetched customer having customer id {} :::: {}", customerID, customer);

        if (customer.isPresent()) {
            return customerService.getPoints(customer.get());
        }
        return ResponseDTO.builderSuper().resultCode(HttpStatus.OK.value()).resultMessage("Customer not exist in our system having this customer id " + customerID).build();
    }

}
