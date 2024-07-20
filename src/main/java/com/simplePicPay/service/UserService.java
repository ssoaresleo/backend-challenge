package com.simplePicPay.service;

import com.simplePicPay.domain.user.User;
import com.simplePicPay.domain.user.UserRequest;
import com.simplePicPay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(UserRequest data) {
        User user = new User(data);
        this.userRepository.save(user);

        return user;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long userId) throws Exception {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not Found"));
    }
}
