package com.wy.moodindex.model.Bean;

public class AuthResult {
    private String accessToken;
    private String refreshToken;
    private String uid;
    private String createdTime;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUid() {
        return uid;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}
