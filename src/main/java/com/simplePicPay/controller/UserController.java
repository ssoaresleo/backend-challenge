package com.simplePicPay.controller;

import com.simplePicPay.domain.user.User;
import com.simplePicPay.domain.user.UserRequest;
import com.simplePicPay.service.UserService;
import com.simplePicPay.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest data) throws Exception {
       User user = this.userService.createUser(data);

       walletService.createWallet(user.getId(), data.balance());

       return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();

        return ResponseEntity.ok(users);
    }
}
