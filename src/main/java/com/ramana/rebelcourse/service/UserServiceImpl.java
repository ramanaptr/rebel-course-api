package com.ramana.rebelcourse.service;

import com.ramana.rebelcourse.model.CustomUser;
import com.ramana.rebelcourse.model.User;
import com.ramana.rebelcourse.payload.request.UserRequest;
import com.ramana.rebelcourse.payload.response.BaseResponse;
import com.ramana.rebelcourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public BaseResponse<Object> registerUser(UserRequest userRequest) {
        try {
            User saved = repository.findByUsername(userRequest.getUsername()).orElse(null);
            if (saved != null) {
                return BaseResponse.setAsFailed("Sorry, username is not available");
            } else if (userRequest.getUsername().trim().contains(" ")) {
                return BaseResponse.setAsFailed("Sorry, username can't use space");
            }
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder().encode(userRequest.getPassword()));
            repository.save(user);
            return BaseResponse.setAsSuccess("Register Success");
        } catch (Exception e) {
            return BaseResponse.setAsFailed(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserDetails loadByUsername(String username) {
        User saved = repository.findByUsername(username).orElse(null);
        if (saved == null) return null;
        return new CustomUser(saved);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
