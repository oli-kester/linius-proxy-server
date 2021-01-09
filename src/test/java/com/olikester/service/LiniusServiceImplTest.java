package com.olikester.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.olikester.model.LiniusAccessToken;

@SpringBootTest
@SuppressWarnings("unused")
class LiniusServiceImplTest {

    @Autowired
    private LiniusService liniusService;

    private LiniusAccessToken testAccessToken;
    static final MultiValueMap<String, String> validSearchParams1 = new LinkedMultiValueMap<>();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
	validSearchParams1.add("query", "testQueryString");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
	testAccessToken = liniusService.signIn();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    @DisplayName("Perform a valid request for an access token")
    void signInTestValid1() {
	LiniusAccessToken accessToken = liniusService.signIn();
	assertNotNull(accessToken);
	assertNotEquals("", accessToken.getToken());
	assertEquals(true, accessToken.isSignedIn());
    }

    @Test
    @DisplayName("Try and send a search with null parameters and token")
    void searchNullParameters() {
	try {
	    liniusService.search(null, null);
	} catch (NullPointerException e) {
	    return;
	}
	fail("Should have thrown NullPointerException");
    }

    @Test
    @DisplayName("Try and send a search with null parameters, but a valid token")
    void searchNullSearchParams() {
	try {
	    liniusService.search(testAccessToken, null);
	} catch (NullPointerException e) {
	    return;
	}
	fail("Should have thrown NullPointerException");
    }

    @Test
    @DisplayName("Try and send a search with valid parameters, but a null token")
    void searchNullToken() {
	try {
	    liniusService.search(null, validSearchParams1);
	} catch (NullPointerException e) {
	    return;
	}
	fail("Should have thrown NullPointerException");
    }

    @Test
    @DisplayName("Send a single valid search, check status code")
    void searchValidBasicTest1() {
	ClientResponse response = liniusService.search(testAccessToken, validSearchParams1);	
	assertNotNull(response);
	assertEquals(HttpStatus.OK, response.statusCode());
    }

}
