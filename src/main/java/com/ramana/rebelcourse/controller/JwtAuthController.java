package com.ramana.rebelcourse.controller;

import com.ramana.rebelcourse.payload.request.LoginRequest;
import com.ramana.rebelcourse.payload.request.UserRequest;
import com.ramana.rebelcourse.payload.response.BaseResponse;
import com.ramana.rebelcourse.service.AuthService;
import com.ramana.rebelcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(
            @RequestBody UserRequest userRequest
    ) {
        BaseResponse<Object> response = userService.registerUser(userRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(
            @RequestBody LoginRequest loginRequest
    ) {
        BaseResponse<Object> response = authService.loginUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<?> userTest(
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(loginRequest);
    }

}