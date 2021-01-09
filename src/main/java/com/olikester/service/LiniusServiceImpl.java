package com.olikester.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.olikester.model.LiniusAccessToken;

@Service
@PropertySource("classpath:api-keys.properties") // API keys are hidden in second properties file.
public class LiniusServiceImpl implements LiniusService {
    
    //Credentials used to access Linius API
    @Value("${linius.x-api-key}")
    private String X_API_KEY;
    @Value("${linius.userName}")
    private String USERNAME;
    @Value("${linius.password}")
    private String PASSWORD;

    @Override
    public LiniusAccessToken signIn() {
	// TODO Auto-generated method stub
	return null;
    }
    
}