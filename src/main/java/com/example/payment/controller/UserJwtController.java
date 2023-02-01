package com.example.payment.controller;

import com.example.payment.controller.vm.LoginVM;
import com.example.payment.domen.User;
import com.example.payment.repository.UserRepository;
import com.example.payment.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserJwtController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserJwtController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginVM loginVM) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername(),
                loginVM.getPassword()));
        User user = userRepository.findByUsername(loginVM.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException("This user not found");
        }
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        Map<Object, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("token", token);

        return ResponseEntity.ok(map);
    }
}
