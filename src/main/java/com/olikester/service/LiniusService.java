package com.olikester.service;

import com.olikester.model.LiniusAccessToken;

public interface LiniusService {
    static final String AUTH_ENDPOINT = "/iam/auth/signin";
    
    /**
     * Sign in our proxy and generate a Linius access token object
     * @return A LiniusAccessToken
     */
    public LiniusAccessToken signIn();
}
