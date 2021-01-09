package com.olikester.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.olikester.model.LiniusAccessToken;

@Service
@PropertySource("classpath:api-keys.properties") // API keys are hidden in second properties file.
public class LiniusServiceImpl implements LiniusService {

    // Credentials used to access Linius API
    @Value("${linius.x-api-key}")
    private String X_API_KEY;
    @Value("${linius.userName}")
    private String USERNAME;
    @Value("${linius.password}")
    private String PASSWORD;

    // Spring WebFlux components
    private final WebClient webClient;
    private static final String API_MIME_TYPE = "application/json";

    public LiniusServiceImpl() {
	this.webClient = WebClient.builder().baseUrl(BASE_URL).defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
		.build();
    }

    @Override
    public LiniusAccessToken signIn() {
	// assemble request body
	Map<String, String> authMap = new HashMap<>();
	authMap.put("userName", USERNAME);
	authMap.put("password", PASSWORD);

	// send login request
	LiniusAccessToken token = webClient.post().uri(AUTH_ENDPOINT).body(BodyInserters.fromValue(authMap)).retrieve()
		.bodyToMono(LiniusAccessToken.class).block();

	return token;
    }

}