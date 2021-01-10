package com.olikester.service;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

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
    private static final MultiValueMap<String, String> validSearchParams1 = new LinkedMultiValueMap<>();
    private static final MultiValueMap<String, String> validEnrichJobsParams1 = new LinkedMultiValueMap<>();
    private static String discoverBody;
    private static String enrichBody;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
	validSearchParams1.add("query", "testQueryString");
	validEnrichJobsParams1.add("id", "[1234]");
	discoverBody = Files.readString(Path.of("src/test/resources/discover-sample-body.json"));
	enrichBody = Files.readString(Path.of("src/test/resources/enrich-sample-body.json"));
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
	ResponseEntity<String> response = liniusService.search(testAccessToken, validSearchParams1);
	assertNotNull(response);
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Send a request for enrich jobs status, check status code")
    void enrichJobsStatusCheck1() {
	ResponseEntity<String> response = liniusService.enrichJobs(testAccessToken, validEnrichJobsParams1);
	assertNotNull(response);
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Send a request for discovery, check status code")
    void discoveryStatusCheck1() {
	ResponseEntity<String> response = liniusService.discover(testAccessToken, discoverBody);
	assertNotNull(response);
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Send a request for enriching, check status code")
    void enrichStatusCheck1() {
	ResponseEntity<String> response = liniusService.enrich(testAccessToken, enrichBody);
	assertNotNull(response);
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
