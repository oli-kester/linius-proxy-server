package com.olikester.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.olikester.model.LiniusAccessToken;
import com.olikester.service.LiniusService;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("unused")
class ProxyControllerTest {

    @Autowired
    private LiniusService liniusService;
    @Autowired
    private LiniusAccessToken liniusAccessToken;

    @Autowired
    private MockMvc mockMvc;

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
    @DisplayName("Send an empty search request to check the connection is valid")
    void checkConnectionValid() throws Exception {
	mockMvc.perform(get("/search")).andExpect(status().isOk());
    }

}
