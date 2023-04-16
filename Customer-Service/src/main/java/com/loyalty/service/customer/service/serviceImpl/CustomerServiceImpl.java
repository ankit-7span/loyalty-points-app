package com.loyalty.service.customer.service.serviceImpl;

import com.loyalty.service.customer.entity.Customer;
import com.loyalty.service.customer.model.BaseResponse;
import com.loyalty.service.customer.model.GetPointsResponse;
import com.loyalty.service.customer.model.ResponseDTO;
import com.loyalty.service.customer.repository.CustomerRepository;
import com.loyalty.service.customer.service.CustomerService;
import com.loyalty.service.customer.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WalletService walletService;

    @Override
    public BaseResponse deductLoyaltyPoints(Long customerID, Long loyaltyPoints) {

        log.info("Adding points to the user's wallet if he purchase amount >= 100 rs");

        Optional<Customer> customer = customerRepository.findById(customerID);

        log.debug("Fetched customer having customer id {} :::: {}", customerID, customer);

        if (customer.isPresent()) {
            walletService.deductLoyaltyPoints(customer.get(), loyaltyPoints);
            return BaseResponse.builder().resultCode(HttpStatus.OK.value()).resultMessage("Saved Data Successfully").build();
        }
        return BaseResponse.builder().resultCode(HttpStatus.OK.value()).resultMessage("Customer not exist in our system having this customer id " + customerID).build();
    }

    @Override
    public ResponseDTO getPoints(Customer customer) {

        GetPointsResponse response = new GetPointsResponse();
        response.setCustomerId(customer.getId());
        response.setCustomerName(customer.getName());
        response.setWalletBalance(customer.getWallet().getLoyaltyPoint());

        log.debug("Response :::: {}", response);

        return ResponseDTO
                .builderSuper()
                .response(response)
                .resultCode(HttpStatus.OK.value())
                .resultMessage("Fetched Data Successfully")
                .build();
    }

    @Override
    public Optional<Customer> getCustomer(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public BaseResponse addPointsToWallet(Long customerID, Long loyaltyPoint) {
        log.info("Adding points to the user's wallet if he purchase amount >= 100 rs");

        Optional<Customer> customer = customerRepository.findById(customerID);

        log.debug("Fetched customer having customer id {} :::: {}", customerID, customer);

        if (customer.isPresent()) {
            walletService.updateWallet(customer.get(), loyaltyPoint);
            return BaseResponse.builder().resultCode(HttpStatus.OK.value()).resultMessage("Saved Data Successfully").build();
        }
        return BaseResponse.builder().resultCode(HttpStatus.OK.value()).resultMessage("Customer not exist in our system having this customer id " + customerID).build();
    }
}