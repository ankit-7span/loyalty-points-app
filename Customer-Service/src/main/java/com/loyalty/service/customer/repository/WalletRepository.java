package com.loyalty.service.customer.repository;

import com.loyalty.service.customer.entity.Customer;
import com.loyalty.service.customer.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
