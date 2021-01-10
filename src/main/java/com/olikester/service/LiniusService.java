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

    public static final String DISCOVER_ENDPOINT = "/discover";
    public static final String ENRICH_ASSETS_ENDPOINT = "/enrich/assets";
    public static final String ENRICH_JOBS_ENDPOINT = "/enrich/jobs";
    public static final String SEARCH_ENDPOINT = "/search";

    /**
     * Sign our proxy service into Linius, and generate a Linius access token object
     * 
     * @return A LiniusAccessToken
     */
    public LiniusAccessToken signIn();

    /**
     * Forward a GET request to Linius, and return the response.
     * 
     * @param accessToken   - The token used to access Linius resources.
     * @param endpoint      - The domain-relative endpoint to send the request to.
     * @param requestParams - The parameters, as defined in the Linius API.
     * @return - The HTTP response to be forwarded to the client.
     */
    public ResponseEntity<String> getRequest(LiniusAccessToken accessToken, String endpoint,
	    MultiValueMap<String, String> requestParams);

    /**
     * Forward a POST request to Linius, and return the response.
     * 
     * @param accessToken - The token used to access Linius resources.
     * @param endpoint    - The domain-relative endpoint to send the request to.
     * @param requestBody - The request body, as defined in the Linius API.
     * @return - The HTTP response to be forwarded to the client.
     */
    public ResponseEntity<String> postRequest(LiniusAccessToken accessToken, String endpoint, String requestBody);
}
