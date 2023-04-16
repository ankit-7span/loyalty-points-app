package com.loyalty.service.customer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.service.customer.model.WalletDetails;
import com.loyalty.service.customer.model.WalletEvent;
import com.loyalty.service.customer.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class WalletConsumer {

    @Autowired
    CustomerService customerService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name:wallet_topics}"
            , groupId = "${spring.kafka.consumer.group-id:wallet}"
    )
    public void consume(String event) {
        log.info(String.format("Wallet event received in stock service => %s", event));
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            WalletEvent walletEvent = objectMapper.readValue(event, WalletEvent.class);
            WalletDetails walletDetails = walletEvent.getWalletDetails();
            customerService.addPointsToWallet(walletDetails.getCustomerId(), walletDetails.getLoyaltyPoints());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
