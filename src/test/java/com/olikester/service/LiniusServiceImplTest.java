package com.olikester.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

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

import com.olikester.model.LiniusAccessToken;

@SpringBootTest
class LiniusServiceImplTest {

    @Autowired
    private LiniusService liniusService;

    private LiniusAccessToken testAccessToken;
    final Map<String, String> validSearchParams1 = Map.of("test", "test");

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
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
    @DisplayName("Send a single valid search")
    void searchValid1() {
	ResponseEntity<String> response = liniusService.search(testAccessToken, validSearchParams1).block();
	assertNotNull(response);
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
