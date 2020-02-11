package com.ramana.rebelcourse.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class JwtResponse implements Serializable {

    private String prefix;
    private String token;
}