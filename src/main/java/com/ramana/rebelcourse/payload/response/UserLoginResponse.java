package com.ramana.rebelcourse.payload.response;

import com.ramana.rebelcourse.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginResponse  {

    private User user;
    private JwtResponse token;
    private JwtResponse refreshToken;
}
