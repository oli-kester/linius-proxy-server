package com.olikester.service;

import com.olikester.model.LiniusAccessToken;

public interface LiniusService {
    static final String BASE_URL = "https://api.lvs.linius.com/v3";
    static final String AUTH_ENDPOINT = "/iam/auth/signin";
    static final String DISCOVER_ENDPOINT = "/discover";
    static final String ENRICH_ASSETS_ENDPOINT = "/enrich/assets";
    static final String ENRICH_JOBS_ENDPOINT = "/enrich/jobs";
    static final String SEARCH_ENDPOINT = "/search";
    
    /**
     * Sign in our proxy and generate a Linius access token object
     * @return A LiniusAccessToken
     */
    public LiniusAccessToken signIn();
}
