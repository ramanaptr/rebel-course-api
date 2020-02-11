package com.ramana.rebelcourse.service;

import com.ramana.rebelcourse.config.JwtRequestFilter;
import com.ramana.rebelcourse.config.JwtTokenUtil;
import com.ramana.rebelcourse.model.CustomUser;
import com.ramana.rebelcourse.model.User;
import com.ramana.rebelcourse.payload.request.LoginRequest;
import com.ramana.rebelcourse.payload.response.BaseResponse;
import com.ramana.rebelcourse.payload.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private static final String TOKEN = "token";

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public PasswordEncoder passwordEncode;

    @Override
    public boolean isAuthenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        } catch (DisabledException | BadCredentialsException e) {
            // throw new Exception("USER_DISABLED", e);
            // throw new Exception("INVALID_CREDENTIALS", e);
            return false;
        }
    }

    @Override
    public BaseResponse<Object> loginUser(LoginRequest loginRequest) {
        try {
            if (!isAuthenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
                return BaseResponse.setAsFailed("Auth Failed");
            }

            User saved = userService.findByUsername(loginRequest.getUsername());
            if (saved == null) {
                return BaseResponse.setAsNotFound("Failed to login, user not found");
            } else if (!passwordEncode.matches(loginRequest.getPassword(), saved.getPassword())) {
                return BaseResponse.setAsFailed("Failed to login, wrong password");
            }

            return getAuthResponse(saved);
        } catch (Exception e) {
            return BaseResponse.setAsFailed(e);
        }
    }

    private BaseResponse<Object> getAuthResponse(User user) {
        UserDetails userDetails = userService.loadByUsername(user.getUsername());
        if (userDetails == null) return BaseResponse.setAsFailed("Failed to get auth");

        String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setPrefix(JwtRequestFilter.BEARER_PREFIX);
        jwtResponse.setToken(token);
        setNewToken(user.getUsername(), token);
        return BaseResponse.setAsSuccess(jwtResponse);
    }

    @Override
    public boolean setNewToken(String username, String token) {
        User user = userService.findByUsername(username);
        if (user == null) return false;

        user.setToken(token);
        userService.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new CustomUser(user);
    }
}
