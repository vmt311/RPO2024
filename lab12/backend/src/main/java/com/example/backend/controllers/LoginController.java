package com.example.backend.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.tools.Utils;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> credentials) {
        String login = credentials.get("login");
        String pwd = credentials.get("password");
        if (!pwd.isEmpty() && !login.isEmpty()) {
            Optional<User> uu = userRepository.findByLogin(login);
            if (uu.isPresent()) {
                User u2 = uu.get();
                String hash1 = u2.password;
                String salt = u2.salt;
                String hash2 = Utils.ComputeHash(pwd, salt);
                if (hash1.equals(hash2)) {
                    String token = UUID.randomUUID().toString();
                    u2.token = token;
                    User u3 = userRepository.saveAndFlush(u2);
                    return new ResponseEntity<Object>(u3, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
    }
}
