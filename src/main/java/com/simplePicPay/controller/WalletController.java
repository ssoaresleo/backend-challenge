package com.simplePicPay.controller;

import com.simplePicPay.domain.wallet.Wallet;
import com.simplePicPay.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallet() {
        List<Wallet> wallets = walletService.getAllWallets();

        return ResponseEntity.ok(wallets);
    }
}
