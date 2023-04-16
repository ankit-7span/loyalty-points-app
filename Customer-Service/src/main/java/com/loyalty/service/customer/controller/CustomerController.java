package com.loyalty.service.customer.controller;

import com.loyalty.service.customer.handler.CustomerHandler;
import com.loyalty.service.customer.model.BaseResponse;
import com.loyalty.service.customer.model.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerHandler customerHandler;

    @PostMapping("/addPointsToWallet/{customerID}/{purchaseAmount}")
    public BaseResponse addPointsToWallet(@PathVariable Long customerID, @PathVariable Long purchaseAmount) {
        return customerHandler.addPointsToWallet(customerID, purchaseAmount);
    }

    @GetMapping("/getPoints/{customerID}")
    public ResponseDTO getPoints(@PathVariable Long customerID) {
        return customerHandler.getPoints(customerID);
    }

}
