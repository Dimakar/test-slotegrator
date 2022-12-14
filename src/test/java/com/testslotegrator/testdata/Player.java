package com.testslotegrator.testdata;

import com.testslotegrator.dto.AuthRequest;
import com.testslotegrator.endpoints.AuthTokenEndpoint;
import io.restassured.http.Header;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Player {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private BasicAuthUser basicAuthUser;
    private Header bearerAuthHeader;

    public Player(String username, String password, BasicAuthUser basicAuthUser) {
        this.username = username;
        this.password = password;
        this.basicAuthUser = basicAuthUser;
    }

    public Header getBearerAuthHeader() {
        if (bearerAuthHeader == null) {
            String accessToken = new AuthTokenEndpoint().auth(this.getBasicAuthUser(), AuthRequest.builder()
                    .grantType("password")
                    .password(this.getEncodedPassword())
                    .username(this.getUsername())
                    .build()).getAccessToken();
            bearerAuthHeader = new Header("Authorization", "Bearer " + accessToken);
        }
        return bearerAuthHeader;
    }

    public String getEncodedPassword() {
        return Base64.getEncoder()
                .encodeToString(this.getPassword()
                        .getBytes(StandardCharsets.UTF_8));
    }
}
