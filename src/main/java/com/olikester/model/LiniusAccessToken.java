package com.olikester.model;

import java.io.Serializable;

public class LiniusAccessToken implements Serializable {
    private static final long serialVersionUID = -531445593780680034L;

    private String token;
    private String userId;
    private boolean signedIn;
    private String refreshToken;
    private int expiresAt;

    /**
     * @return the token
     */
    public String getToken() {
	return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
	this.token = token;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
	return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
	this.userId = userId;
    }

    /**
     * @return the signedIn
     */
    public boolean isSignedIn() {
	return signedIn;
    }

    /**
     * @param signedIn the signedIn to set
     */
    public void setSignedIn(boolean signedIn) {
	this.signedIn = signedIn;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
	return refreshToken;
    }

    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
    }

    /**
     * @return the expiresAt
     */
    public int getExpiresAt() {
	return expiresAt;
    }

    /**
     * @param expiresAt the expiresAt to set
     */
    public void setExpiresAt(int expiresAt) {
	this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
	return "LiniusAccessToken [token=" + token + ", userId=" + userId + ", signedIn=" + signedIn + ", refreshToken="
		+ refreshToken + ", expiresAt=" + expiresAt + "]";
    }

}
