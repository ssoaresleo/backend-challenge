package com.simplePicPay.repositories;

import com.simplePicPay.domain.transactions.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
