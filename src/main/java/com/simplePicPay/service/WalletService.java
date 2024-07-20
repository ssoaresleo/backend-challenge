package com.simplePicPay.service;

import com.simplePicPay.domain.user.User;
import com.simplePicPay.domain.wallet.Wallet;
import com.simplePicPay.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    public void createWallet(Long userId, BigDecimal balance) throws Exception {
        User user = userService.findUserById(userId);

        Wallet wallet = new Wallet();

        wallet.setUser(user);
        wallet.setBalance(balance);

        walletRepository.save(wallet);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public void updateWallet() {}

    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.getWalletByUserId(userId);
    }
}
