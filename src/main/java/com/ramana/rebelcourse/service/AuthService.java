package com.ramana.rebelcourse.service;

import com.ramana.rebelcourse.payload.request.LoginRequest;
import com.ramana.rebelcourse.payload.response.BaseResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    boolean isAuthenticate(String username, String password) throws Exception;

    boolean setNewToken(String username, String token);

    BaseResponse<Object> loginUser(LoginRequest loginRequest);
}
