package com.olikester.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.olikester.model.LiniusAccessToken;
import com.olikester.service.LiniusService;

@Configuration
public class AppConfig {
    
    @Autowired
    private LiniusService liniusService;
    
    @Bean //holds our authentication token
    public LiniusAccessToken liniusAccessToken() {
	return liniusService.signIn();
    }
}
