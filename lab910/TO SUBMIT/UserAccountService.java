package com.lab910.service;

import com.lab910.model.UserAccount;
import com.lab910.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserAccountService {
    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount register(UserAccount user) {
        return userAccountRepository.save(user);
    }

    public UserAccount login(String email, String password) {
        return userAccountRepository.findByEmailAndPassword(email, password);
    }


    private final List<UserAccount> APPLICATION_USERS = Arrays.asList(
            new UserAccount("admin@gmail.com", new BCryptPasswordEncoder().encode("123"), "Admin", "User"),
            new UserAccount("user@gmail.com", new BCryptPasswordEncoder().encode("456"), "Regular", "User")
    );
    public UserDetails findUserByEmail(String email){
        UserAccount user = APPLICATION_USERS.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
