package com.simplePicPay.domain.transactions;

import java.math.BigDecimal;

public record TransactionRequest(BigDecimal amount, Long payerId, Long payeeId) {
}
