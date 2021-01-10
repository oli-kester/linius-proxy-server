package com.olikester.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    private static String discoverBody;
    private static String enrichBody;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
	discoverBody = Files.readString(Path.of("src/test/resources/discover-sample-body.json"));
	enrichBody = Files.readString(Path.of("src/test/resources/enrich-sample-body.json"));
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
    @DisplayName("Send an empty search request to check the connection to Linius is valid")
    void checkConnectionValid() throws Exception {
	mockMvc.perform(get(ProxyController.SEARCH_ENDPOINT).queryParam("query", "test")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("size", is(50)));
    }

    @Test
    @DisplayName("Send a search, checking we're influencing the page size of the results")
    void searchTestCheckPageSize() throws Exception {
	mockMvc.perform(get(ProxyController.SEARCH_ENDPOINT).queryParam("query", "test2").queryParam("pageSize", "1")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("size", is(1)));
    }

    @Test
    @DisplayName("Send a search request from a different IP and check it's blocked")
    void checkUnlistedIpSearchIsBlocked() throws Exception {
	mockMvc.perform(get(ProxyController.SEARCH_ENDPOINT).queryParam("query", "test2").queryParam("pageSize", "1")
		.contentType(MediaType.APPLICATION_JSON).with(request -> {
		    request.setRemoteAddr("192.168.1.106");
		    return request;
		})).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Send an enrich jobs status request")
    void testEnrichJobsStatus1() throws Exception {
	mockMvc.perform(get(ProxyController.ENRICH_JOBS_ENDPOINT).queryParam("id", "123")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("jobs", empty()));
    }

    @Test
    @DisplayName("Send a discover request")
    void testAddDiscovery1() throws Exception {
	mockMvc.perform(
		post(ProxyController.DISCOVER_ENDPOINT).content(discoverBody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("name", is("Big Buck Bunny")));
    }

    @Test
    @DisplayName("Send an enrich request for an already enriched asset")
    void testEnrichAsset1() throws Exception {
	mockMvc.perform(post(ProxyController.ENRICH_ASSETS_ENDPOINT).content(enrichBody)
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
		.andExpect(jsonPath("message", is("Asset is already indexed")));
    }
}
