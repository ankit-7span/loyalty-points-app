package com.loyalty.service.customer.service.serviceImpl;

import com.loyalty.service.customer.entity.Customer;
import com.loyalty.service.customer.entity.Wallet;
import com.loyalty.service.customer.repository.WalletRepository;
import com.loyalty.service.customer.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;


    @Override
    public void deductLoyaltyPoints(Customer customer, Long loyaltyPoints) {
        Wallet wallet = customer.getWallet();
        if (wallet.getLoyaltyPoint() != 0 && wallet.getLoyaltyPoint() >= loyaltyPoints) {
            Long points = wallet.getLoyaltyPoint() - loyaltyPoints;
            wallet.setLoyaltyPoint(points);
            walletRepository.save(wallet);
            log.debug("Updating wallet having wallet id {} :::: {}", wallet.getId(), wallet);
        }
    }


    @Override
    public Wallet updateWallet(Customer customer, Long loyaltyPoint) {
        Wallet wallet = customer.getWallet();
        Long points = wallet.getLoyaltyPoint() + loyaltyPoint;
        wallet.setLoyaltyPoint(points);
        log.debug("Updating wallet having wallet id {} :::: {}", wallet.getId(), wallet);
        walletRepository.save(wallet);
        return wallet;
    }

    private Integer getLoyaltyPoint(Long purchaseAmount) {
        int amount = Math.round(purchaseAmount);
        int getPoint = amount / 100;
        return getPoint * 4;
    }
}
