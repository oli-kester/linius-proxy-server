package com.olikester.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // will block all requests aside from IP addresses given below.
    private static final String ALLOWED_IPS = "hasIpAddress('127.0.0.1') or hasIpAddress('192.168.1.1')";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers("/**").access(ALLOWED_IPS).anyRequest().authenticated().and().csrf()
		.disable();
    }
}