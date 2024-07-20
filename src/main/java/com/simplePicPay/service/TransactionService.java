package com.simplePicPay.service;

import com.simplePicPay.domain.transactions.TransactionRequest;
import com.simplePicPay.domain.transactions.Transactions;
import com.simplePicPay.domain.user.User;
import com.simplePicPay.domain.user.UserEnum;
import com.simplePicPay.domain.wallet.Wallet;
import com.simplePicPay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    public void validateTransaction(User payer, BigDecimal amount) throws Exception {
        if(payer.getUserType() == UserEnum.LOJISTAS) {
            throw  new Exception("Usuários do tipo logistas não podem realizar transferências.");
        }

        Wallet wallet = walletService.getWalletByUserId(payer.getId());

        if(wallet.getBalance().compareTo(amount) < 0) {
            throw  new Exception("Saldo insuficiente!");
        }
    }

    public Transactions createTransaction(TransactionRequest transaction) throws Exception {
        User payer = userService.findUserById(transaction.payerId());
        User payee = userService.findUserById(transaction.payeeId());

        this.validateTransaction(payer, transaction.amount());

        boolean isAuthorizate = this.authorizateTransaction();

        if(!isAuthorizate) {
            throw  new Exception("Transação não autorizada, tente novamente mais tarde.");
        }

        Transactions newTransaction = new Transactions();

        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setAmount(transaction.amount());
        newTransaction.setLocalDateTime(LocalDateTime.now());

        Wallet walletPayer = walletService.getWalletByUserId(payer.getId());
        Wallet walletPayee = walletService.getWalletByUserId(payee.getId());

        walletPayer.setBalance(walletPayer.getBalance().subtract(transaction.amount()));
        walletPayee.setBalance(walletPayee.getBalance().add(transaction.amount()));

        this.transactionRepository.save(newTransaction);

        return newTransaction;
    }

    private Boolean authorizateTransaction() {
        String url = "https://util.devi.tools/api/v2/authorize";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getStatusCode() == HttpStatus.OK;
    }
}
