package com.lab910.controller;

import com.lab910.model.UserAccount;
import com.lab910.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    // POST method to register a new user account
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userAccountService.register(user);
    }

    // POST method for user login
    @PostMapping("/login")
    public UserAccount login(@RequestParam String email, @RequestParam String password) {
        return userAccountService.login(email, password);
    }
}
