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

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public BaseResponse<Object> registerUser(UserRequest userRequest) {
        try {
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder().encode(userRequest.getPassword()));
            User saved = repository.save(user);
            return BaseResponse.setAsSuccess(saved);
        } catch (Exception e) {
            return BaseResponse.setAsFailed(e);
        }
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
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
