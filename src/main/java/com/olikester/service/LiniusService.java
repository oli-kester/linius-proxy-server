package com.olikester.service;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.olikester.model.LiniusAccessToken;

/**
 * Forwards select requests on to Linius
 * 
 * @author Oliver Reynolds
 *
 */
public interface LiniusService {
    static final String BASE_URL = "https://api.lvs.linius.com/v3";
    static final String AUTH_ENDPOINT = "/iam/auth/signin";
    static final String DISCOVER_ENDPOINT = "/discover";
    static final String ENRICH_ASSETS_ENDPOINT = "/enrich/assets";
    static final String ENRICH_JOBS_ENDPOINT = "/enrich/jobs";
    static final String SEARCH_ENDPOINT = "/search";

    /**
     * Sign our proxy service into Linius, and generate a Linius access token object
     * 
     * @return A LiniusAccessToken
     */
    public LiniusAccessToken signIn();

    /**
     * Forward a search request to Linius, and return the response.
     * 
     * @param accessToken   - The token used to access Linius resources.
     * @param requestParams - The search parameters, as defined in the Linius API.
     * @return - The HTTP response to be forwarded to the client.
     */
    public ResponseEntity<String> search(LiniusAccessToken accessToken, MultiValueMap<String, String> requestParams);

    /**
     * Forward an enrich jobs status request to Linius, and return the response.
     * 
     * @param accessToken   - The token used to access Linius resources.
     * @param requestParams - The search parameters, as defined in the Linius API.
     * @return - The HTTP response to be forwarded to the client.
     */
    public ResponseEntity<String> enrichJobs(LiniusAccessToken accessToken,
	    MultiValueMap<String, String> requestParams);

    /**
     * Forward a discovery request to Linius, and return the response.
     * 
     * @param accessToken - The token used to access Linius resources.
     * @param requestBody - The discovery parameters, as defined in the Linius API.
     * @return - The HTTP response to be forwarded to the client.
     */
    public ResponseEntity<String> discover(LiniusAccessToken accessToken, String requestBody);

    /**
     * Forward an enrich request to Linius, and return the response.
     * 
     * @param accessToken - The token used to access Linius resources.
     * @param requestBody - The enrich parameters, as defined in the Linius API.
     * @return - The HTTP response to be forwarded to the client.
     */
    public ResponseEntity<String> enrich(LiniusAccessToken accessToken, String requestBody);
}
