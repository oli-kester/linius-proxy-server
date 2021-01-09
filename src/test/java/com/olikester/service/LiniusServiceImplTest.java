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

import com.olikester.model.LiniusAccessToken;

@SpringBootTest
class LiniusServiceImplTest {

    @Autowired
    private LiniusService liniusService;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
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

}
