package com.simplePicPay.repositories;

import com.simplePicPay.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet getWalletByUserId(Long userId);
}
