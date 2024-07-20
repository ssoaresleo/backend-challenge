package com.simplePicPay.domain.user;

import java.math.BigDecimal;

public record UserRequest(String firstName, String lastName, String document, String email, String password, UserEnum userType, BigDecimal balance) {
}
