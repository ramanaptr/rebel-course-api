package com.ramana.rebelcourse.service;

import com.ramana.rebelcourse.model.User;
import com.ramana.rebelcourse.payload.request.UserRequest;
import com.ramana.rebelcourse.payload.response.BaseResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    UserDetails loadByUsername(String username);

    BaseResponse<Object> registerUser(UserRequest userRequest);

    User save(User user);
}
