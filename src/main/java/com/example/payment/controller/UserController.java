package com.example.payment.controller;

import com.example.payment.domen.User;
import com.example.payment.repository.UserRepository;
import com.example.payment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user) {
        if (!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Password length little 4", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserName(user.getUsername())){
            return new ResponseEntity("This userName already registered", HttpStatus.BAD_REQUEST);
        }
            return ResponseEntity.ok(userService.save(user));

    }

    private Boolean checkPasswordLength(String password) {
        return password.length() >= 4;
    }
}
