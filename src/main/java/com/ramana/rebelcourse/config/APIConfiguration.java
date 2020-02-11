package com.ramana.rebelcourse.config;

import org.springframework.beans.factory.annotation.Value;

public class APIConfiguration {

    @Value("${api.host}")
    protected String host;

    @Value("${api.token}")
    protected String token;

    @Value("${api.language.en}")
    protected String en;

    @Value("${api.language.id}")
    protected String id;

    public APIConfiguration() {
    }
}
