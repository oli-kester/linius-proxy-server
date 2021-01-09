package com.olikester.service;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.olikester.model.LiniusAccessToken;

/**
 * Forwards select requests on to Linius
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
     * @param requestParams - The search parameters, as defined in the Linius API.
     * @param accessToken   - The token used to access Linius resources.
     * @return - The HTTP response to be forwarded to the client.
     */
    public ResponseEntity<String> search(LiniusAccessToken accessToken,
	    MultiValueMap<String, String> requestParams);

}
