package com.kraftmatic.geodesgemstones.util;

import org.springframework.stereotype.Component;

@Component
public class TokenHolder {

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    private String accessToken;
    private String refreshToken;
}
